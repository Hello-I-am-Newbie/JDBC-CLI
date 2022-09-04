package com.danny.app.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.danny.app.entitiy.Notice;
import com.danny.app.service.NoticeService;

public class NoticeConsole {

	private NoticeService service; // 데이터가 필요하기 때문 
	private int page;
	private String searchWord = "";
	private String searchField = "TITLE";
	
	
	public NoticeConsole() {
		
		service = new NoticeService();	// 생성될 때 데이터를 받아온다 
		page = 1;
	} // constructor
	
	public void printNoticeList() throws ClassNotFoundException, SQLException{
		
		List<Notice> list = service.getList(page, searchField, searchWord);
		int count = service.getCount(page, searchField, searchWord);
		int lastPage = count/10;
		lastPage = count%10 == 0 ? lastPage : lastPage+1;
		lastPage = count/10 == 0 ? 1 : count%10;
		
		System.out.println("--------------------------------------");
		System.out.printf("<공지사항> 총 %d 게시글\n", count);
		System.out.println("--------------------------------------");
		
		for(Notice n : list) {
			System.out.printf("%d. %s / %s / %s\n",
								n.getId(),
								n.getTitle(),
								n.getWriterId(),
								n.getRegDate());
			
		} // for
		System.out.println("--------------------------------------");
		System.out.printf("              %d/%d page\n", page, lastPage );
	} // printNoticeList

	public int inputNoticeMenu() {
		
		Scanner scan = new Scanner(System.in);
		System.out.printf("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기 /5.검색 /6.종료>");
		String menu_ = scan.nextLine();
		
		int menu = Integer.parseInt(menu_);
		
		return menu;
	} // inputNoticeMenu

	public void movePrevList() {
		if(page == 1) {
			System.out.println("------------------------------");
			System.out.println("이전 페이지가 없습니다.");
			System.out.println("------------------------------");
			return;
		} // if
		
		page--;			
	} // movePrevList

	public void moveNextList() throws ClassNotFoundException, SQLException {
		int count = service.getCount(page, searchField, searchWord);
		int lastPage = count/10; 
		lastPage = count%10 == 0 ? lastPage : lastPage+1;

		if(page == lastPage) {
			System.out.println("------------------------------");
			System.out.println("다음 페이지가 없습니다.");
			System.out.println("------------------------------");
			
			return;
		} 
		page++;
	} // moveNextList

	public void inputSearchWord() {
		Scanner scan = new Scanner(System.in);
		System.out.println("검색 범주(id/title/writerId)중 하나를 입력하세요.");
		System.out.println(">");
		searchField  = scan.nextLine();
		
		System.out.println("검색어");
		System.out.println(">");
		searchWord = scan.nextLine();
		
	} // inputSearchWord()

	public void showContent() throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("상세조회할 ID 값을 입력하세요");
		System.out.println(">");
		
		String searchId_ = scan.nextLine();
		int searchId = Integer.parseInt(searchId_);
		
		String content = service.getContent(searchId);
		
		System.out.println(content);
		
		
	} // showContent

} // end class

