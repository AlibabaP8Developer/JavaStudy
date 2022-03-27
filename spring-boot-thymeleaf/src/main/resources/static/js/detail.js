function getWindowSize(){
  return {
    ww:document.documentElement.clientWidth || document.body.clientWidth,
    wh:document.documentElement.clientHeight || document.body.clientHeight,
  }
}