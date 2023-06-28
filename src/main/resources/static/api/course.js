function getCourseList (params) {
    return $axios({
        url: '/course/page',
        method: 'get',
        params
    })
}

// 修改---启用禁用接口
function enableOrDisableEmployee (params) {
    return $axios({
        url: '/course',
        method: 'put',
        data: { ...params }
    })
}

// 新增---添加课程
function addCourse (params) {
    return $axios({
        url: '/course',
        method: 'post',
        data: { ...params }
    })
}

// 修改---添加课程
function editCourse (params) {
    return $axios({
        url: '/course/tgrade',
        method: 'put',
        data: { ...params }
    })
}
function queryTeacherByCid (cid) {
    return $axios({
        url: `/course/${cid}`,
        method: 'get'
    })
}