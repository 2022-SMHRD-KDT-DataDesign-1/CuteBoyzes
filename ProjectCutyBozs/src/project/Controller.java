package project;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javazoom.jl.player.MP3Player;

public class Controller {
	int now = 0;

	boolean bb = true;
	DAO dao = new DAO();
	Scanner sc = new Scanner(System.in);
	// mp3 파일 재생 라이브러리 -- > musicPlayer 참조
	MP3Player mp3 = new MP3Player();

	// MovieDTO값을 받는 ArrayList 초기화
	ArrayList<MovieDTO> movielist = new ArrayList<>();
	
	
	String basicPath = "C://movie/";
	
	public void moviesound() {
		
		movielist.add(new MovieDTO("상", "곡성", "C://movie/곡성 - 곡성.mp3"));

	}
	



	// 회원가입
	public void join(PlayerDTO dto) {
		int row = dao.join(dto);
		// row
		if (row > 0)
			System.out.println("회원가입 성공!");
		else
			System.out.println("회원가입 실패!");
	}
	
	// 로그인
	public void login(PlayerDTO dto) {
		
		// DAO 객체 생성
		DAO dao = new DAO();
		// 게임시작 여부 변수 초기화
		String yn;
		// 난이도 변수 초기화
		int dif;

		boolean res = dao.login(dto);

		if (res) {
			System.out.println("로그인 성공");

			System.out.print("게임시작 Y / N >> ");
			yn = sc.next();
			if (yn.equals("Y")) {
				System.out.println("룰어쩌고 이러쿵저러쿵");
				System.out.println("룰어쩌고 이러쿵저러쿵");
				System.out.println("룰어쩌고 이러쿵저러쿵");
				try {
					// 3초 지연하는 코드
					TimeUnit.SECONDS.sleep(3);

				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("난이도 선택");
				System.out.println("[1] 상 [2] 중 [3] 하  >> ");
				dif = sc.nextInt();

				Controller ct = new Controller();
				if (dif == 1) {
					
					
					// ct.안에있는 play메소드 호출
					play();
				} else if (dif == 2) {
					System.out.println("중 ㅋㅋ");
				} else if (dif == 3) {
					System.out.println("하 ㅋㅋ");
				}

			} else if (yn.equals("N")) {
				System.out.println("로그아웃");

			}

		} else {
			System.out.println("로그인 실패");
		}

	}

	
	public MovieDTO play() {
		System.out.println("재밌는 게임");
		
		// 값이 추가된 Arraylist 호출
		moviesound();

		
	    MovieDTO vo = movielist.get(now);
		
		if(mp3.isPlaying()) {
			mp3.stop();
		}
		
		mp3.play(vo.getPath());
		
		return vo;
			
		
	}
	
	public void playOff() {
		bb = false;
	}
	

}
