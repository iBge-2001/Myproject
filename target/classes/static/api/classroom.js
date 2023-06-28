function getClassroomList (params) {
    return $axios({
        url: '/classroom/page',
        method: 'get',
        params
    })
}

// 修改---启用禁用接口
function enableOrDisableEmployee (params) {
    return $axios({
        url: '/employee',
        method: 'put',
        data: { ...params }
    })
}

// 新增---添加员工
function addClassroom (params) {
    return $axios({
        url: '/classroom',
        method: 'post',
        data: { ...params }
    })
}

// 修改---添加员工
function editEmployee (params) {
    return $axios({
        url: '/classroom',
        method: 'put',
        data: { ...params }
    })
}


function lazyQueryApi (classroom) {
    return $axios({
        url: `/classroom/${classroom}`,
        method: 'get'
    })
}