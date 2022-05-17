// 유저아이디 및 비밀번호 중복체크하기

let valid = {
    userId: {
        state: false, // true면 중복 X, false면 중복 O
        msg: "" // false로 중복됐을 때 사용자에게 보여줄 메세지
    },
    password: {
        state: false,
        msg: ""
    },
    email: {
        state: false,
        msg: ""
    }
}

// 유저아이디 이벤트 리스너
$("#userId").focusout(() => {
    usernameSameCheck();
});

// 비밀번호 이벤트 리스너
$("#password").focusout(() => {
    passwordSameCheck();
});

// 비밀번호 중복체크 이벤트 리스너
$("#same-password").focusout(() => {
    passwordSameCheck();
});

// 이메일 중복체크 이벤트 리스너
$("#email").focusout(() => {
    emailSameCheck();
});

// 유효성 검사 함수
function validation() {
    if (valid.userId.state && valid.password.state && valid.email.state) { // 중복이 아니라면
        return true;
    } else { // 중복이라면
        let errorMsgs = ``;

        errorMsgs += `${valid.userId.msg}<br/>`;
        errorMsgs += `${valid.password.msg}<br/>`;
        errorMsgs += `${valid.email.msg}<br/>`;

        $(".my_error_box").html(errorMsgs);
        $(".my_error_box").removeClass("my_hidden");
        return false;
    }
}

// 아이디 중복체크 함수
async function usernameSameCheck() {
    // 1. userId 값 찾기
    let userId = $("#userId").val();

    // 2. fetch로 DB에 동일한 userId가 있는지 확인하기
    let response = await fetch(`/api/user/userid-same-check?userId=${userId}`);
    let responseParse = await response.json();

    if (response.status === 200) {
        if (!responseParse) { // 이미 사용 중인 아이디가 있으면
            valid.userId.state = false;
            valid.userId.msg = "이미 사용 중인 유저아이디입니다.";
        } else { // 사용 중인 아이디가 없으면
            valid.userId.state = true;
            valid.userId.msg = "";
        }
    } else {
        alert(responseParse);
    }
}

// 비밀번호 중복체크 함수
function passwordSameCheck() {
    // 1. password 값 찾기
    let password = $("#password").val();
    // 2. same-password 값 찾기
    let samePassword = $("#same-password").val();
    // 3. 두개의 값을 비교하기
    if (password === samePassword) {
        valid.password.state = true;
        valid.password.msg = "";
    } else {
        valid.password.state = false;
        valid.password.msg = "패스워드가 일치하지 않습니다.";
    }
}

// 이메일 중복체크 함수
async function emailSameCheck() {
    // 1. email 값 찾기
    let email = $("#email").val();

    // 2. fetch로 DB에 동일한 email이 있는지 확인하기
    let response = await fetch(`/api/user/email-same-check?email=${email}`);
    let responseParse = await response.json();

    if (response.status === 200) {
        if (!responseParse) { // 이미 사용 중인 이메일이 있으면
            valid.email.state = false;
            valid.email.msg = "이미 사용 중인 이메일입니다.";
        } else { // 사용 중인 이메일이 없으면
            valid.email.state = true;
            valid.email.msg = "";
        }
    } else {
        alert(responseParse);
    }
}