import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class BookImpl implements Book {
	Scanner scan = new Scanner(System.in);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	Map<String , BookVO> map = new HashMap<>(); 
	DecimalFormat df = new DecimalFormat("000");
	private Calendar now = Calendar.getInstance();
	
	@Override
	public void borrow() {									
		try {
			String bname;
			System.out.println("빌릴 책 이름을 입력하세요 : ");
			bname = br.readLine();
			
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String num = it.next();
				BookVO vo = map.get(num);
				
				if (vo.getName().startsWith(bname)) {
					if(vo.getStock() == 0) {
						System.out.println("재고 수가 없습니다.");
						break;
					}
					vo.setStock(vo.getStock()-1);
					System.out.print(num + "\t");
					System.out.print(vo.getName() + "\t");
					System.out.print(vo.getGenre() + "\t");
					System.out.print(vo.getStock() + "\n");
					System.out.println("대여완료");
					String a = String.format("%1$tF %1$tA", now);
					System.out.print("대여시간 : "+a);
					System.out.print("반납시간 : "+(a+7));
					System.out.println();	
				}
			}
		} catch (Exception e) {

		}
	}

	@Override
	public void back() {									
		// 반납 할때 재고를를 존재로 확인
		System.out.println("반납할 책 이름입력");
		String s = scan.next();
		Iterator<String> it = map.keySet().iterator();

		// 키값 가져오기

		while (it.hasNext()) {
			String key = it.next();
			BookVO value = map.get(key);
			
			if (s.equals(value.getName())) {
				if (!value.isRental()) {
					System.out.println("대여중이 아닙니다");
					
				} else if (value.isRental()) {
					System.out.println("정상적으로 반납 되었습니다");
					value.setStock(value.getStock()+1);
					value.setRental(false);
					
				}
			}
		}

		// if (!isExist2)
		// System.out.println("도서 존재 X");
	}

	@Override
	public void bookSearch() {								
		Iterator<String> it = map.keySet().iterator();
		int setnum=0;
		int count=0;
		
		try {
			System.out.println("검색할 책 이름 : ");
			String s1 = br.readLine();
			
			while (it.hasNext()) {
				String s2=df.format(++setnum);
				String key = it.next();
				BookVO bookvo = map.get(key);
				if (bookvo.getName().indexOf(s1)>-1) {
					System.out.print(key+"\t");
					System.out.print(bookvo.getGenre()+"\t");
					System.out.print(bookvo.getName()+"\t");
					System.out.print(bookvo.getStock()+"\n");
					count++;
				}
			}
			if (count==0) {
				System.out.println("검색된 책이 없습니다.");
				return;
			}
			System.out.println();
			System.out.println("총 "+count+"건의 책이 검색되었습니다.");
			System.out.println();
			
		} catch (IOException e) {
			
		}
	}

	@Override
	public void printBookList() {							
		Iterator<String> it=map.keySet().iterator();
		while (it.hasNext()) {
			String key=it.next();
			BookVO value=map.get(key);
			System.out.print(key+"\t"+value.getGenre()+"\t"+value.getName()+"\t"+value.getStock()+"\n");
		}
	}

	@Override
	public void bookUpdate() {								
		System.out.println("\n책 정보 수정...");
		
		try {
			String name;
			System.out.print("책 이름?");
			name = br.readLine();
			
			BookVO vo = map.get(name);
			if(vo.getName()==null) {
				System.out.println("존재하지 않는 이름입니다.");
				return;
			}
			System.out.println("책 이름?");
			vo.setName(br.readLine());
			
			System.out.print("작가 이름은?");
			vo.setWriter(br.readLine());
			
			System.out.print("책 장르는? ");
			vo.setGenre(br.readLine());
			
			System.out.print("재고 수는?");
			vo.setStock(scan.nextInt());
			
			System.out.println("수정 완료...");
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println();
	}

	@Override
	public void bookAdd() {									
		System.out.println("\n 책을 등록하겠습니다...");
		
		try {
			String code;		//code값이 String값임 
			BookVO vo=new BookVO();
			
			System.out.print("코드는?");
			code=br.readLine();
			
			if(map.containsKey(code)) { //코드가 있으면 (키값이있으면) 빠져나가고 
				System.out.println("등록된 책 입니다.\n재고를 추가하시겠습니까?\n1. Y  2.N");
				if(scan.nextInt() == 1) {
					vo = map.get(code);
					System.out.println("몇 권 추가하시겠습니까?");
					vo.setStock(vo.getStock() + scan.nextInt());
				}
				return;
			}
			System.out.print("책 이름은?");
			vo.setName(br.readLine());
			
			System.out.print("작가 이름은?");
			vo.setWriter(br.readLine());
			
			System.out.print("책 장르는? ");
			vo.setGenre(br.readLine());
			
			System.out.print("재고 수는?");
			vo.setStock(scan.nextInt());

			map.put(code, vo); //map은 put이 데이터 추가 / arraylist는 add데이터 추가 
			System.out.println("책이 등록되었습니다\n");
			
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	@Override
	public void bookDelete() {									
		System.out.println("삭제할 책의 이름 입력");
		
		String s = scan.next();
		Iterator<String> it = map.keySet().iterator();


		while (it.hasNext()) {
			String key = it.next();
			BookVO value = map.get(key);

			if (s.equals(value.getName())) {
				map.remove(key);
				System.out.println(s+"책 삭제 완료");
				value.setStock(value.getStock()-1);
			}
		}
	}
	
	@Override
	public void bestRecommanded() {
		Iterator<String> it = map.keySet().iterator();
		
		int maxRecommand = 0;
		BookVO voMax = new BookVO();
		
		do {
			String key = it.next();
			BookVO vo = map.get(key);
			if (vo.getRecommand() >= maxRecommand) {
				maxRecommand = vo.getRecommand();
				voMax = vo;
			}
		} while(it.hasNext());
		System.out.println("가장 많이 추천된 책은 " + voMax.getWriter() + "작가의 " + voMax.getName() + "입니다. 추천 수: " + voMax.getRecommand());
	}

	@Override
	public void userRecommand() {
		
		try {
		Iterator<String> it = map.keySet().iterator();
		String bname;
		System.out.println("추천할 책 이름을 입력하세요 : ");
		bname = br.readLine();
		BookVO vo = new BookVO();
		
		while(it.hasNext()) {
			String key = it.next();
			vo = map.get(key);
			if(vo.getName().equalsIgnoreCase(bname))
				vo.setRecommand(vo.getRecommand() + 1);
		}
		
		System.out.println("책이 추천되었습니다.");
		
		} catch(Exception e){
			
		}
	}
}