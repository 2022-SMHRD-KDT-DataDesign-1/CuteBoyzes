package project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javazoom.jl.player.MP3Player;

public class Controller {
	int now = 0;
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

	// 회원가입
	public void join(PlayerDTO dto) {
		int row = dao.join(dto);
		// row
		if (row > 0) {
			System.out.println("회원가입 성공!");
			if (mp3.isPlaying()) {
				mp3.stop();
			}
			mp3.play(".\\\\bgm\\opening.mp3");
			try {
				// 1초 지연하는 코드
				TimeUnit.SECONDS.sleep(3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			mp3.stop();
		}
		else
			System.out.println("회원가입 실패!");
	}

	// 로그인
	public void login(PlayerDTO dto) {
		MP3Player mp3 = new MP3Player();

		// DAO 객체 생성
		DAO dao = new DAO();

		// 게임시작 여부 변수 초기화
		String yn;

		// 난이도 변수 초기화
		int lebel;

		boolean res = dao.login(dto);

		if (res) {

			System.out.println("로그인 성공");
			if (mp3.isPlaying()) {
				mp3.stop();
			}
			mp3.play(".\\\\bgm\\opening.mp3");
			try {
				// 1초 지연하는 코드
				TimeUnit.SECONDS.sleep(3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			mp3.stop();

			System.out.print("게임시작 Y / N >> ");
			yn = sc.next();
			if (mp3.isPlaying()) {
				mp3.stop();
			}
			mp3.play(".\\\\bgm\\select.mp3");
			try {
				// 1초 지연하는 코드
				TimeUnit.SECONDS.sleep(2);

			} catch (Exception e) {
				e.printStackTrace();
			}
			mp3.stop();

			if (yn.equals("Y") || yn.equals("y")) {
				System.out.println("      들려주는 명대사를 듣고");
				if (mp3.isPlaying()) {
					mp3.stop();
				}
				mp3.play(".\\\\bgm\\rule.mp3");

				try {
					// 1초 지연하는 코드
					TimeUnit.SECONDS.sleep(1);

				} catch (Exception e) {
					e.printStackTrace();
				}
				mp3.stop();

				System.out.println("    어떤 영화인지 맞춰보세요!!!");

				if (mp3.isPlaying()) {
					mp3.stop();
				}
				mp3.play(".\\\\bgm\\rule.mp3");

				try {
					// 3초 지연하는 코드
					TimeUnit.SECONDS.sleep(1);

				} catch (Exception e) {
					e.printStackTrace();
				}
				mp3.stop();

				System.out.println("(출제범위: CUTEBOY들이 본 영화 위주)");

				if (mp3.isPlaying()) {
					mp3.stop();
				}
				mp3.play(".\\\\bgm\\rule.mp3");

				try {
					// 3초 지연하는 코드
					TimeUnit.SECONDS.sleep(1);

				} catch (Exception e) {
					e.printStackTrace();
				}
				mp3.stop();

				System.out.println("난이도 선택");
				System.out.println("[1] 상 [2] 중 [3] 하  >> ");
				lebel = sc.nextInt();

				Controller ct = new Controller();
				if (lebel == 1) {

					System.out.println("난이도 >> 상");
					// ct.안에있는 play메소드 호출
					play_db(dto, 1);
				} else if (lebel == 2) {
					System.out.println("난이도 >> 중");

					play_db2(dto, 1);

				} else if (lebel == 3) {
					System.out.println("난이도 >> 하");
					play_db3(dto, 1);
				}

			} else if (yn.equals("N")) {
				System.out.println("종료합니당");

			}

		} else {
			System.out.println("로그인 실패");
		}

	}

	public void play_db(PlayerDTO dto, int range) {

		// 사용하려는 라이브러리 생성
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		MP3Player mp3 = new MP3Player();

		// DAO 객체생성 (Controller와 연결해야하기때문에)
		DAO dao = new DAO();

		// play_db 에 들어가는 답(answer)변수 초기화
		String answer = "";

		// 테이블의 Score 컬럼에 더해줄값 초기화
		int sum = 0;
		// 테이블의 Score 컬럼에 들어갈값 초기화
		int score = 0;
		// 랜덤 함수의 최대값 정의
		int max = 32;

		// 답을 담아줄 값 정의
		String[] arr = new String[10];

		// arr 에 들어가는 인덱스 값 변수
		int cnt = 0;

		// 순서를 정해주는 list
		int[] raarr = new int[10];

		// 중복없이 raarr에 순서를 담아주는 과정
		for (int i = 0; i < raarr.length; i++) {

			raarr[i] = rd.nextInt(32) + range;

			for (int j = 0; j < i; j++) {
				if (raarr[i] == raarr[j]) {
					i--;
					break;
				}
			}
		}

		// 1~10번 문제까지 반복하는 실행문
		for (int i = 0; i < arr.length; i++) {
			System.out.print("\n" + (i + 1) + "번 문제 ");

			if (mp3.isPlaying()) {
				mp3.stop();
			}

			// 랜덤한 값에서도 위치에 맞는 값을 가져오기위해 MovieDTO타입의 dto_movie를 초기화 해준다
			// = dao.s_movie_dao -> dao에서 만든 sql문을 통해서 값을 가져오게 하기 위해 선언
			MovieDTO dto_movie = dao.s_movie_dao(raarr[i]);

			// mp3.play(경로==dto_movie.getPath())
			mp3.play(dto_movie.getPath());
			try {
				TimeUnit.SECONDS.sleep(8);

			} catch (Exception e) {
				e.printStackTrace();
			}
			mp3.stop();

			System.out.println("다시 듣겠습니까 ? >> ");

			// 위에 전역변수로 초기화했던 answer을 선언
			// sc.nextLine() -> 띄어쓰기 인식이라고 하는데 인식이 안됨 ㅋ
			answer = sc.nextLine();

			// 다시 듣겠다고 하면
			if (answer.equals("네") || answer.equals("ㅇㅇ")) {
				// dto_movie에 dao.s_movie_dao() 에 raarr[i] 랜덤하게 넣은 값을 담아줌
				dto_movie = dao.s_movie_dao(raarr[i]);
				mp3.play(dto_movie.getPath());
				try {
					TimeUnit.SECONDS.sleep(8); // 8

				} catch (Exception e) {
					e.printStackTrace();
				}
				mp3.stop();
			}

			System.out.print("정답입력 >>");

			// 답을 담아주는 arr[]에 정답을 담아준후 밑에 if문을 통해 비교해준다
			arr[cnt] = sc.nextLine();

			if (arr[cnt].equals(dto_movie.getTitle())) {
				System.out.println("정답 ㅋㅋ ");

				if (mp3.isPlaying()) {
					mp3.stop();
				}
				mp3.play(".\\\\bgm\\hahaha.mp3");

				try {
					// 3초 지연하는 코드
					TimeUnit.SECONDS.sleep(1);

				} catch (Exception e) {
					e.printStackTrace();
				}
				mp3.stop();
				// 정답일때마다 10점씩 누적 축적
				score += 10;
				try {
					TimeUnit.SECONDS.sleep(2); // 2

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("땡 ㅋㅋ");
				if (mp3.isPlaying()) {
					mp3.stop();
				}
				mp3.play(".\\\\bgm\\fail.mp3");

				try {
					// 3초 지연하는 코드
					TimeUnit.SECONDS.sleep(1);

				} catch (Exception e) {
					e.printStackTrace();
				}
				mp3.stop();
				score = 0; // dto_movie.getTitle() 로 db에 저장되어있는 타이틀명을 가져온다
				System.out.println("정답은 " + dto_movie.getTitle() + "입니당");
				try {
					TimeUnit.SECONDS.sleep(2); // 2

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			sum += score;
			sum += sum / 20;
			cnt++;

		}

		System.out.println("점수 >> " + sum);

		// dao안에 있는 score_dao(변수1, 변수2)에 값을 보내줌
		dao.score_dao(sum, dto);

	}

	public void play_db2(PlayerDTO dto, int range) {

		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		MP3Player mp3 = new MP3Player();

		DAO dao = new DAO();

		String answer = "";
		int cnt = 0;
		int sum = 0;
		int score = 0;
		int max = 22;
		String[] arr = new String[10];
		int[] raarr = new int[10];

		for (int i = 0; i < raarr.length; i++) {

			raarr[i] = rd.nextInt(max) + range;

			for (int j = 0; j < i; j++) {
				if (raarr[i] == raarr[j]) {
					i--;
					break;
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {

			System.out.print("\n" + (i + 1) + "번 문제 ");

			if (mp3.isPlaying()) {
				mp3.stop();
			}

			// // // /// / // / dao.h_movie_dao(1) 임시로 dao.s_movie_dao(1); 로 바꿈 !!!!!
			MovieDTO dto_movie = dao.j_movie_dao(raarr[i]);
			mp3.play(dto_movie.getPath());
			try {
				TimeUnit.SECONDS.sleep(7);

			} catch (Exception e) {
				e.printStackTrace();
			}
			mp3.stop();

			System.out.println("다시 듣겠습니까 ? >> ");
			answer = sc.nextLine();

			if (answer.equals("네") || answer.equals("ㅇㅇ")) {

				dto_movie = dao.j_movie_dao(raarr[i]);
				mp3.play(dto_movie.getPath());
				try {
					TimeUnit.SECONDS.sleep(7);

				} catch (Exception e) {
					e.printStackTrace();
				}
				mp3.stop();
			}

			System.out.print("정답입력 >>");

			arr[cnt] = sc.nextLine();

			if (arr[cnt].equals(dto_movie.getTitle())) {
				System.out.println("정답 ㅋㅋ ");
				if (mp3.isPlaying()) {
					mp3.stop();
				}
				mp3.play(".\\\\bgm\\hahaha.mp3");

				try {
					// 3초 지연하는 코드
					TimeUnit.SECONDS.sleep(1);

				} catch (Exception e) {
					e.printStackTrace();
				}
				mp3.stop();
				score += 10;
				try {
					TimeUnit.SECONDS.sleep(2);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("땡 ㅋㅋ");
				if (mp3.isPlaying()) {
					mp3.stop();
				}
				mp3.play(".\\\\bgm\\fail.mp3");

				try {
					// 3초 지연하는 코드
					TimeUnit.SECONDS.sleep(1);

				} catch (Exception e) {
					e.printStackTrace();
				}
				mp3.stop();
				score = 0;
				System.out.println("정답은 " + dto_movie.getTitle() + "입니당");
				try {
					TimeUnit.SECONDS.sleep(2);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			sum += score;
			sum += sum / 10;
			cnt++;
		}
		System.out.println("점수 >> " + sum);

		dao.score_dao(sum, dto);

	}

	public void play_db3(PlayerDTO dto, int range) {

		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		MP3Player mp3 = new MP3Player();

		DAO dao = new DAO();
		Artlist al = new Artlist();

		String answer = "";
		int max = 12;

		int cnt = 0;
		int sum = 0;
		int score = 0;
		String[] arr = new String[10];
		int[] raarr = new int[10];
		String[] artlist = { al.gokseong(), al.senven(), al.inner(), al.leesoonsin(), al.idol(), al.veteran(), al.ssg(),
				al.yg(), al.kindgirl(), al.yerimi() };

		while (true) {

			for (int i = 0; i < raarr.length; i++) {
				raarr[i] = i + 1;

			}
			for (int i = 0; i < arr.length; i++) {
				System.out.print("\n" + (i + 1) + "번 문제 ");

				if (mp3.isPlaying()) {
					mp3.stop();
				}

				MovieDTO dto_movie = dao.h_movie_dao(raarr[i]);
				mp3.play(dto_movie.getPath());
				try {
					TimeUnit.SECONDS.sleep(7);

				} catch (Exception e) {
					e.printStackTrace();
				}
				mp3.stop();

				System.out.println("다시 듣겠습니까 ? >> ");
				answer = sc.nextLine();

				if (answer.equals("네") || answer.equals("ㅇㅇ") || answer.equals("ㅇ") || answer.equals("응")
						|| answer.equals("어") || answer.equals("웅") || answer.equals("ㅇㅇㅇ")) {

					dto_movie = dao.h_movie_dao(raarr[i]);
					mp3.play(dto_movie.getPath());
					try {
						TimeUnit.SECONDS.sleep(7);

					} catch (Exception e) {
						e.printStackTrace();
					}
					mp3.stop();
				}

				System.out.print("정답입력 >>");

				arr[cnt] = sc.nextLine();

				if (arr[cnt].equals(dto_movie.getTitle())) {

					System.out.println(al.identity());
					System.out.println();
					System.out.println();
					System.out.println(" 정답 입니다 ! ! !             ");
					System.out.println();
					System.out.println();
					if (mp3.isPlaying()) {
						mp3.stop();
					}
					mp3.play(".\\\\bgm\\hahaha.mp3");

					try {
						TimeUnit.SECONDS.sleep(1);

					} catch (Exception e) {
						e.printStackTrace();
					}
					mp3.stop();
					score += 10;
					try {
						TimeUnit.SECONDS.sleep(2);

					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println(" 땡 ㅋ ㅋ              ");
					if (mp3.isPlaying()) {
						mp3.stop();
					}
					mp3.play(".\\\\bgm\\fail.mp3");

					try {
						TimeUnit.SECONDS.sleep(1);

					} catch (Exception e) {
						e.printStackTrace();
					}
					mp3.stop();

					score = 0;
					System.out.println(artlist[i]);
					System.out.println();
					System.out.println();
					System.out.println(" 정답은 " + dto_movie.getTitle() + "입니다 ! ! !      ");
					try {
						TimeUnit.SECONDS.sleep(2);

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				sum += score;
				cnt++;
			}
			System.out.println();
			System.out.println();
			System.out.println("     당신의 ");
			if (mp3.isPlaying()) {
				mp3.stop();
			}
			mp3.play(".\\\\bgm\\rule.mp3");

			try {
				// 3초 지연하는 코드
				TimeUnit.SECONDS.sleep(1);

			} catch (Exception e) {
				e.printStackTrace();
			}
			mp3.stop();
			System.out.println("   점수는 ?!?! ");
			if (mp3.isPlaying()) {
				mp3.stop();
			}
			mp3.play(".\\\\bgm\\rule.mp3");

			try {
				// 3초 지연하는 코드
				TimeUnit.SECONDS.sleep(1);

			} catch (Exception e) {
				e.printStackTrace();
			}
			mp3.stop();
			System.out.println(sum + "점 입니다 ! ! ! !");
			if (mp3.isPlaying()) {
				mp3.stop();
			}
			mp3.play(".\\\\bgm\\sum.mp3");
			try {
				// 1초 지연하는 코드
				TimeUnit.SECONDS.sleep(3);

			} catch (Exception e) {
				e.printStackTrace();
			}
			mp3.stop();
			System.out.println();

			dao.score_dao(sum, dto);
			System.out.println("한번 더 플레이 하시렵니까 ? >> Y / N ");
			String yorn = sc.next();

			if (yorn.equals("Y")) {

				if (mp3.isPlaying()) {
					mp3.stop();
				}
				mp3.play(".\\\\bgm\\opening.mp3");
				try {
					// 1초 지연하는 코드
					TimeUnit.SECONDS.sleep(3);

				} catch (Exception e) {
					e.printStackTrace();
				}
				mp3.stop();
				continue;
			} else if (yorn.equals("N")) {
				System.out.println(" 메인 화면으로 돌아갑니다 - !");
				try {
					TimeUnit.SECONDS.sleep(1);

				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}

		}
	}

}
