const sendMsgApi = (email) => {
    return $axios({
        url: `/user/sendMsg/${email}`,
        method: 'post'
    })
}
function createUserApi(data){
    return $axios({
        'url':'/user/create',
        'method':'put',
        data
    })
}

function loginApi(data){
    return $axios({
        'url':'/user/login',
        'method':'post',
        data
    })
}
function updateApi(data){
    return $axios({
        'url':'/user/update',
        'method':'put',
        data
    })
}
// 修改---启用禁用接口
function enableOrDisableUser (params) {
    return $axios({
        url: '/user/status',
        method: 'put',
        data: { ...params }
    })
}