function menuClick(menu){
	location.href='./'+menu;
}
function logout(){
	if(confirm("로그아웃 하시겠습니까?")){
		location.href="logout";
	}
}