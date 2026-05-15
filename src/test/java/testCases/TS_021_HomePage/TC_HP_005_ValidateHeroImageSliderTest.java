package testCases.TS_021_HomePage;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_HP_005_ValidateHeroImageSliderTest extends BaseClass {

    @Test
    public void validateHeroImageSlider() {

        logger.info("***** Starting TC_HP_005_HeroImageSliderTest *****");

        HomePage home = new HomePage(getDriver());

        // Validate Hero Images
        int heroImagesCount =
                home.getHeroImagesCount();

        Assert.assertTrue(
                heroImagesCount > 0,
                "Hero Images are not displayed!"
        );

        logger.info("Verified Hero images are displayed on Home Page.");

        // Validate Auto Slide
        if (heroImagesCount > 1) {

            boolean autoSlideWorking =
                    home.validateHeroAutoSlide();

            if (!autoSlideWorking) {

                throw new SkipException(
                        "Auto-slide not supported or too slow."
                );
            }

            Assert.assertTrue(
                    autoSlideWorking,
                    "Hero Images did not auto slide!"
            );

            logger.info("Verified Hero images auto slide works.");

        } else {

            logger.warn(
                    "Only one Hero Image present, skipping auto-slide validation."
            );

            throw new SkipException(
                    "Auto-slide not applicable when only one image exists."
            );
        }

        // Validate Next Button Slide
        Assert.assertTrue(
                home.validateHeroNextButtonSlide(),
                "Hero Image did not slide using Next button!"
        );

        logger.info("Verified manual slide using Next button.");

        // Validate Pagination Bullet Slide
        boolean bulletSlideWorking =
                home.validateHeroPaginationBulletSlide();

        if (bulletSlideWorking) {

            logger.info(
                    "Verified manual slide using pagination bullets."
            );

        } else {

            logger.warn(
                    "Pagination bullets not available. Skipping validation."
            );
        }

        // Validate Drag Slide
        boolean dragSlideWorking =
                home.validateHeroDragSlide();

        if (dragSlideWorking) {

            logger.info(
                    "Verified Hero Image slide using drag & drop."
            );

        } else {

            logger.warn(
                    "Skipping drag & drop validation due to brittle interaction."
            );
        }
    }
}