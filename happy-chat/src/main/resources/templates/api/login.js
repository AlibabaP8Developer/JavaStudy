function loginApi(data) {
  return $axios({
    'url': '/user/login',
    'method': 'post',
    data
  })
}

function getUserIdApi(){
  return $axios({
    'url': '/user/getUserId',
    'method': 'get',
  })
}
