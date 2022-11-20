import Slider from "react-slick";
import styled from "@emotion/styled";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import caroL from "@assets/carouselL.png";
import caroR from "@assets/carouselR.png";
import defaultImg from "@assets/defaultImg.png";
import "@styles/Login/slick-dots.scss";
import styles from "@styles/Login/Login.module.scss";
import carousel_1 from "@assets/carousel_1.gif";
import carousel_2 from "@assets/carousel_2.gif";
import carousel_3 from "@assets/carousel_3.gif";
import carousel_4 from "@assets/carousel_4.gif";
import carousel_5 from "@assets/carousel_5.gif";

const Image = styled.img`
  width: 70%;
  margin: auto;
`;

const StyledSlider = styled(Slider)`
  height: 100%;
  width: 90%;
  margin: auto;
  .slick-prev::before,
  .slick-next::before {
    opacity: 0;
    display: none;
  }
`;

const Pre = styled.div`
  width: 30px;
  height: 30px;
  position: absolute;
  z-index: 3;
`;

const NextTo = styled.div`
  width: 30px;
  height: 30px;
  position: absolute;
  z-index: 3;
`;

const CustomCarousel = () => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    nextArrow: (
      <NextTo>
        <img src={caroR} />
      </NextTo>
    ),
    prevArrow: (
      <Pre>
        <img src={caroL} />
      </Pre>
    ),
  };

  return (
    <div className={styles["carousel"]}>
      <StyledSlider {...settings} dotsClass="slick-dots">
        <div>
          <Image src={carousel_1} />
        </div>
        <div>
          <Image src={carousel_2} />
        </div>
        <div>
          <Image src={carousel_3} />
        </div>
        <div>
          <Image src={carousel_4} />
        </div>
        <div>
          <Image src={carousel_5} />
        </div>
      </StyledSlider>
    </div>
  );
};

export default CustomCarousel;
