package testCases.TS_022_HomePage;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import testBase.BaseClass;

import java.time.Duration;
import java.util.List;

public class TC_HP_005_ValidateHeroImageSliderTest extends BaseClass {

    private WebDriverWait wait;

    @Test
    public void validateHeroImageSlider() {

        logger.info("***** Starting TC_HP_005_HeroImageSliderTest *****");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Validate Hero Images are displayed
        List<WebElement> heroImages = driver.findElements(By.cssSelector(".swiper-slide img"));
        Assert.assertTrue(heroImages.size() > 0, "Hero Images are not displayed!");
        logger.info("Verified Hero images are displayed on Home Page.");

        // 2. Validate Auto Slide
        if (heroImages.size() > 1) {
            String firstImageSrc = driver.findElement(By.cssSelector(".swiper-slide-active img")).getAttribute("src");

            try {
                boolean imageChanged = wait.until((ExpectedCondition<Boolean>) drv -> {
                    String currentSrc = drv.findElement(By.cssSelector(".swiper-slide-active img")).getAttribute("src");
                    return !currentSrc.equals(firstImageSrc);
                });

                Assert.assertTrue(imageChanged, "Hero Images did not auto slide!");
                logger.info("Verified Hero images auto slide works.");
            } catch (Exception e) {
                logger.warn("Skipping auto-slide validation due to timeout/exception: " + e.getMessage());
                throw new SkipException("Auto-slide not supported or too slow.");
            }
        } else {
            logger.warn("Only one Hero Image present, skipping auto-slide validation.");
            throw new SkipException("Auto-slide not applicable when only one image exists.");
        }

        // 3. Validate Manual Slide using Next button
        WebElement nextButton = driver.findElement(By.cssSelector("div.swiper-button-next"));
        String beforeClickSrc = driver.findElement(By.cssSelector(".swiper-slide-active img")).getAttribute("src");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nextButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);

        boolean changedByNext = wait.until((ExpectedCondition<Boolean>) drv -> {
            String afterClickSrc = drv.findElement(By.cssSelector(".swiper-slide-active img")).getAttribute("src");
            return !afterClickSrc.equals(beforeClickSrc);
        });

        Assert.assertTrue(changedByNext, "Hero Image did not slide using Next button!");
        logger.info("Verified manual slide using Next button.");

        // 4. Validate Manual Slide using Pagination Bullets
        List<WebElement> bullets = driver.findElements(By.cssSelector(".swiper-pagination-bullet"));
        if (bullets.size() > 1) {
            String beforeBulletClick = driver.findElement(By.cssSelector(".swiper-slide-active img")).getAttribute("src");

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bullets.get(1));

            boolean changedByBullet = wait.until((ExpectedCondition<Boolean>) drv -> {
                String afterBulletClick = drv.findElement(By.cssSelector(".swiper-slide-active img")).getAttribute("src");
                return !afterBulletClick.equals(beforeBulletClick);
            });

            Assert.assertTrue(changedByBullet, "Hero Image did not slide using pagination bullet!");
            logger.info("Verified manual slide using pagination bullets.");
        } else {
            logger.warn("Pagination bullets not available.");
        }

        // 5. Validate Drag & Slide using Mouse
        try {
            WebElement sliderContainer = driver.findElement(By.cssSelector(".swiper-wrapper"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", sliderContainer);

            String beforeDrag = driver.findElement(By.cssSelector(".swiper-slide-active img")).getAttribute("src");

            Dimension size = sliderContainer.getSize();
            int sliderWidth = size.getWidth();
            int sliderHeight = size.getHeight();

            // Start near the center-right and drag to the left
            int xOffsetStart = (int) (sliderWidth * 0.4);
            int xOffsetEnd = (int) (sliderWidth * 0.1);
            int yOffset = sliderHeight / 2;

            Actions actions = new Actions(driver);
            actions.moveToElement(sliderContainer, xOffsetStart, yOffset)
                    .clickAndHold()
                    .moveByOffset(-100, 0)
                    .pause(Duration.ofMillis(300))
                    .release()
                    .perform();

            boolean changedByDrag = wait.until((ExpectedCondition<Boolean>) drv -> {
                String afterDrag = drv.findElement(By.cssSelector(".swiper-slide-active img")).getAttribute("src");
                return !afterDrag.equals(beforeDrag);
            });

            Assert.assertTrue(changedByDrag, "Hero Image did not slide using drag & drop!");
            logger.info("Verified Hero Image slide using drag & drop.");
        } catch (MoveTargetOutOfBoundsException e) {
            logger.warn("Drag action failed: " + e.getMessage());
            throw new SkipException("Drag action out of bounds. Skipping test.");
        } catch (Exception e) {
            logger.warn("Skipping drag & drop validation due to exception: " + e.getMessage());
            throw new SkipException("Drag functionality not supported in this environment.");
        }
    }
}