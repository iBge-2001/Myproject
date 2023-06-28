function getCidByUser(){
    return $axios({
        url: `/teacher/cid`,
        method: 'get'
    })
}
const getGradeByCidApi =(params)=>  {
    return $axios({
        url: `/teacher/byCid`,
        method: 'get',
        params
    })
}
function getSidByPidApi(){
    return $axios({
        url: `/teacher/getStudentByCid`,
        method: 'get'
    })
}