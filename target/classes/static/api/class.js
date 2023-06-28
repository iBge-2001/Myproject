const getClassApi =(params)=>  {
    return $axios({
        url: `/class/getAll`,
        method: 'get',
        params
    })
}
function getPidApi(){
    return $axios({
    url: `/academy/pid`,
    method: 'get'
})
}
//获取班级信息
const getByClassApi=(params)=>{
    return $axios({
        url: `/class/detail`,
        method: 'get',
        params
    })
}
function queryStudentBySid (aid) {
    return $axios({
        url: `/student/${sid}`,
        method: 'get'
    })
}