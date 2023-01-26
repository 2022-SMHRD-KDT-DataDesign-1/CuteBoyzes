package project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javazoom.jl.player.MP3Player;

public class Controller {
	int now = 0;
	// bb은 뭘 뜻 하는 거지?
	boolean bb = true;

	int sum = 0;
	int score = 0;
	int cnt = 0;
	//
	DAO dao = new DAO();
	Scanner sc = new Scanner(System.in);
	// mp3 파일 재생 라이브러리 -- > musicPlayer 참조
	MP3Player mp3 = new MP3Player();

	// MovieDTO값을 받는 ArrayList 초기화
	ArrayList<MovieDTO> movielist = new ArrayList<>();

	String basicPath = "C://movie/";

	public void moviesound() {

		// movielist.add(new MovieDTO("상", "곡성", "C://movie/곡성 - 곡성.mp3"));

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
		int lebel;

		boolean res = dao.login(dto);

		if (res) {
			System.out.println("로그인 성공");

			System.out.print("게임시작 Y / N >> ");
			yn = sc.next();
			if (yn.equals("Y")) {
				System.out.println("들려주는 명대사를 듣고");
				System.out.println("어떤 영화인지 맞춰보세요!!!");
				System.out.println("(출제범위: CUTEBOY들이 본 영화 위주)");
				try {
					// 3초 지연하는 코드
					TimeUnit.SECONDS.sleep(3);

				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("난이도 선택");
				System.out.println("[1] 상 [2] 중 [3] 하  >> ");
				lebel = sc.nextInt();

				Controller ct = new Controller();
				if (lebel == 1) {

					System.out.println("상 ㅋㅋ");
					// ct.안에있는 play메소드 호출
					play_db(dto,1);
				} else if (lebel == 2) {
					System.out.println("중 ㅋㅋ");
					
				} else if (lebel == 3) {
					System.out.println("하 ㅋㅋ");
				}

			} else if (yn.equals("N")) {
				System.out.println("로그아웃");

			}

		} else {
			System.out.println("로그인 실패");
		}

	}

	public void play_db(PlayerDTO dto, int range) {
		
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		MP3Player mp3 = new MP3Player();

		DAO dao = new DAO();
		String answer="";
		int cnt = 0;
		int sum = 0;
		int score = 0;
		String[] arr = new String[10];
		int[] raarr = new int[10];

//		for (int i = 0; i < raarr.length; i++) {
//			
//			raarr[i] = rd.nextInt(0,1) + 1;
//			for (int j = 0; j < i; j++) {
//				if (raarr[i] == raarr[j]) {
//					i--;
//					break;
//				}
//			}
//		}
		System.out.println("ss");

		for (int i = 0; i < arr.length; i++) {
			System.out.print("\n" + (i + 1) + "번 문제 ");

			if (mp3.isPlaying()) {
				mp3.stop();
			}

			// // //  /// / // / dao.h_movie_dao(1) 임시로 dao.s_movie_dao(1); 로 바꿈 !!!!!
			MovieDTO dto_movie = dao.h_movie_dao(1);
			mp3.play(dto_movie.getPath());
			try {
				TimeUnit.SECONDS.sleep(8);

			} catch (Exception e) {
				e.printStackTrace();
			}
			mp3.stop();

			System.out.println("다시 듣겠습니까 ? >> ");
			answer = sc.nextLine();
			
			if(answer.equals("네") || answer.equals("ㅇㅇ")) {
				
				dto_movie = dao.h_movie_dao(1);
				mp3.play(dto_movie.getPath());
				try {
					TimeUnit.SECONDS.sleep(8);

				} catch (Exception e) {
					e.printStackTrace();
				}
				mp3.stop();
			}else {
				System.out.print("정답입력 >>");
				
				arr[cnt] = sc.nextLine();
				
				if(arr[cnt].equals(dto_movie.getTitle())) {
					System.out.println("정답 ㅋㅋ ");
					score+=10;
					try {
						TimeUnit.SECONDS.sleep(2);

						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("땡 ㅋㅋ");
					score = 0;
					System.out.println("정답은 "+dto_movie.getTitle()+"입니당");
					try {
						TimeUnit.SECONDS.sleep(2);

						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
				sum +=score;
				cnt++;
				
			}
			
			

		}
		System.out.println("점수 >> "+sum);
		
		dao.score_dao(sum, dto);

	}

	public void playOff() {
		bb = false;
	}

}
