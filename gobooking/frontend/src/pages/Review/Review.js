import React from "react";

import './style.css'

import ReviewStars from "./components/ReviewStars";
import Button from "./components/Button";
import CommentBox from "./components/CommentBox";
import TitleBox from "./components/TitleBox";

function Review(){
    return (
      <div className={"review"}>
          <ReviewStars />
          <TitleBox />
          <CommentBox />
          <Button classname={"button--submit"} text={"Submit"}/>
      </div>
    );
}
export default Review;