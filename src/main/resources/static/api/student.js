function getStudentApi()  {
    return $axios({
        url: `/student/getMe`,
        method: 'get'
    })
}
const getClassStudentApi =(params)=>  {
    return $axios({
        url: `/student/getAll`,
        method: 'get',
        params
    })
}
const getGradeApi =(params)=>  {
    return $axios({
        url: `/grade/one`,
        method: 'get',
        params
    })
}
function updateMeApi (params) {
    return $axios({
        url: '/student',
        method: 'put',
        data: { ...params }
    })
}
const getDormitoryByClassApi =(params)=>  {
    return $axios({
        url: `/dormitory/GetOne`,
        method: 'get',
        params
    })
}
const getDormitoryApi =(params)=>  {
    return $axios({
        url: `/dormitory`,
        method: 'get',
        params
    })
}
const getGradeByPApi =(params)=>  {
    return $axios({
        url: `/grade/byProfession`,
        method: 'get',
        params
    })
}
function getStudentByIdApi(sid){
    return $axios({
        url: `/student/${sid}`,
        method: 'get'
    })
}
function addStudent (params) {
    return $axios({
        url: '/student',
        method: 'post',
        data: { ...params }
    })
}
