function updatePasswordApi(data){
    return $axios({
        'url':'/user/updatePassword',
        'method':'put',
        data
    })
}