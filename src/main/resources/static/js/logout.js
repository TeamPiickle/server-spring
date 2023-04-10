const logout = document.getElementById("logout");

logout.addEventListener('click', async () => {
    await fetch(`${document.location.origin}/admin/logout`, {
        method: 'PATCH',
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        },
    });
    window.location.href = `${document.location.origin}/admin/login`;
})