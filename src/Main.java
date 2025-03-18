import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserImpl user = new UserImpl();
		BookImpl book = new BookImpl();
		int ch;
		
		while(true) {
			if(user.loginMember() == null ) { 							//로그인이 되지 않은 경우
				do {
					System.out.print("1.로그인 2.회원가입 3.종료 => ");
					ch = sc.nextInt();
				} while(ch<1||ch>3);
				
				if(ch==3) continue;
				
				switch (ch) {
					case 1:{
						user.logIn(); 
						break;
					}
					case 2:{
						user.join(); 
						break;
					}
					
				}
				
				
			} else if(user.loginMember().getId() == "admin" ) { 		//로그인 한 경우
				
				do {
				System.out.print("1.도서 관리 2.회원관리 3.로그아웃 4.종료 ");
				ch = sc.nextInt();
				} while (ch<1||ch>4);
				
				if(ch==4) continue;
				
				switch (ch) {
					case 1: do {
							System.out.print("1.도서등록 2.도서수정 3.도서삭제 4.도서검색 5.도서리스트 6.복귀  ");
							ch = sc.nextInt();
							} while(ch<1||ch>6);
					
							if(ch==6) break;
					
							switch (ch) {
							
							case 1:book.bookAdd(); break; 					// 도서 등록 
							case 2:book.bookUpdate(); break;				// 도서 수정
							case 3:book.bookDelete(); break;				// 도서 삭제
							case 4:book.bookSearch(); break;				// 도서명검색
							case 5:book.printBookList(); break;				// 도서리스트
							}break;
					case 2: do {
								System.out.print("1.회원 리스트 2.이름검색 3.회원삭제 4.복귀  ");
								ch = sc.nextInt();
							} while(ch<1||ch>4);
							
							if(ch==4) break;
				
							switch (ch) {
							case 1: user.printUserList();break;		// 회원리스트
							case 2: user.userSecrch();break;		// 아이디검색
							case 3: user.userDelete();break;		// 유저 삭제
							}
							break;
					case 3:user.logOut(); break;					//로그아웃
				}
			}
			
			// 일반 사용자로 로그인한 경우
			else{
				
				do {
					System.out.print("1.도서 대여 2.도서반납 3.도서검색 4.로그아웃 5.정보수정 6.탈퇴 7.종료 8.책 추천 9.가장 많이 추천된 책");
					ch = sc.nextInt();
				} while (ch < 1 || ch > 9);
				
				if (ch == 7) continue;
		
				switch (ch) {
					case 1:
						book.borrow();
						break; 						// 도서 대여
					case 2:
						book.back();
						break; 						// 도서 검색
					case 3:
						book.bookSearch();
						break; 						// 도서 검색
					case 4:
						user.logOut();				// 로그아웃
						break;
					case 5: 
						user.userUpdate();	        // 정보 수정
						break;
					case 6: 
						user.userDelete();			// 탈퇴
						break;
					case 8:
						book.userRecommand();
						break;
					case 9:
						book.bestRecommanded();
						break;
				}

			}
		}
	}
}
