function queryAcademyByAid (aid) {
    return $axios({
        url: `/academy/${aid}`,
        method: 'get'
    })
}
//获取学院
function getAcademyApi() {
    return $axios({
        url: '/academy/',
        method: 'get',
    })
}
//获取学院信息
const getByAcademyApi=(params)=>{
    return $axios({
        url: `/academy/detail`,
        method: 'get',
        params
    })
}
