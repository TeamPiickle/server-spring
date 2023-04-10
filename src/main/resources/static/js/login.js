const button = document.getElementById("loginButton");
button.addEventListener('click', async () => {
    console.log("눌렸는데... 진짜루..");
    const id = document.getElementById("floatingInput").value;
    const password = document.getElementById("floatingPassword").value;
    const loginPayload = { id, password};
    const res = await fetch(`${document.location.origin}/admin/login`, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        },
        body: JSON.stringify(loginPayload)
    });
    if (res.status !== 200) {
        window.alert("이메일/비밀번호가 올바르지 않습니다.");
        return;
    }
    window.location.href = `${document.location.origin}/admin`;
})