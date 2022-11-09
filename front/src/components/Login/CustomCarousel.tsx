import styles from "@styles/Login/Login.module.scss";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import styled from "@emotion/styled";
import caroL from "@assets/carouselL.png"
import caroR from "@assets/carouselR.png"
import defaultImg from "@assets/defaultImg.png";

const Image = styled.img`
  width: 60%;
  height: 60%;
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
  }`;

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
    autoplay : true,
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
  }
  return (
    <div className={styles["carousel"]}>
      <StyledSlider {...settings}>
        <div>
          <Image src={defaultImg} />
        </div>
        <div>
          <Image src={defaultImg} />
        </div>
        <div>
          <Image src={defaultImg} />
        </div>
        <div>
          <Image src={defaultImg} />
        </div>
        <div>
          <Image src={defaultImg} />
        </div>
      </StyledSlider>
    </div>
  );
}

export default CustomCarousel;