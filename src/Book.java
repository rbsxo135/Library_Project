public interface Book {
	public void borrow();			// 책을 빌린다.		
	public void back();				// 책을 반납한다.		
	public void bookSearch();		// 책을 검색한다.		
	public void printBookList();	// 책의 리스트를 출력한다.
	public void bookUpdate();		// 책을 수정한다.		
	public void bookAdd();			// 서고에 책을 추가한다.	
	public void bookDelete();		// 등록된 책을 지운다.	
	public void bestRecommanded();  // 가장 추천 수가 많은 책을 출력한다.
	public void userRecommand();    // 유저가 책을 추천한다.
}