package project;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javazoom.jl.player.MP3Player;

public class Main extends Artlist {

	public static void main(String[] args) {
		MP3Player mp3 = new MP3Player();

		Scanner sc = new Scanner(System.in);
		int select = 0;
		// 컨트롤러 객체 생성
		Controller ct = new Controller();

		Artlist al = new Artlist();
		if (mp3.isPlaying()) {
			mp3.stop();
		}
		mp3.play(".\\\\bgm\\countion.mp3");

		try {

			al.main2(args);
			mp3.stop();
			
		} catch (InterruptedException e1) {
		}

		try {
			TimeUnit.SECONDS.sleep(2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();


		while (select != 4) {

			if (mp3.isPlaying()) {
				mp3.stop();
			}
			mp3.play(".\\\\bgm\\intro.mp3");
			try {
				TimeUnit.SECONDS.sleep(1);

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(
					" =========================================");
			// .\\bgm\intro.mp3
			System.out
					.print(" [1]회원가입 [2]로그인 [3]랭킹확인 [4]프로그램 종료  >>  ");
			System.out.println();
			System.out.println("  =========================================");

			select = sc.nextInt();

			if (select == 1) {
				System.out.println("=========================================");
				System.out.print("ID를 입력해주세요 >> ");
				System.out.println();
				System.out.println("=========================================");
				String id = sc.next();
				System.out.println("=========================================");
				System.out.print("Password를 입력해주세요 >> ");
				System.out.println();
				System.out.println("=========================================");
				String pw = sc.next();
				System.out.println("=========================================");
				System.out.print("NickName를 입력해주세요 >> ");
				System.out.println();
				System.out.println("=========================================");
				String nick = sc.next();
				mp3.stop();
				PlayerDTO dto = new PlayerDTO(id, pw, nick);
				ct.join(dto);

			} else if (select == 2) {
				System.out.println("=========================================");
				System.out.print("ID를 입력해주세요 >> ");
				System.out.println();
				System.out.println("=========================================");
				String id = sc.next();
				System.out.println("=========================================");
				System.out.print("Password를 입력해주세요 >> ");
				System.out.println();
				System.out.println("=========================================");
				String pw = sc.next();
				mp3.stop();
				PlayerDTO dto = new PlayerDTO(id, pw);
				ct.login(dto);
				if (ct.bb == false) {
					break;
				}

			}
			// 랭킹확인
			else if (select == 3) {
				DAO dao = new DAO();
				System.out.println("====== 랭킹확인 ======");
				System.out.println("rank" + "\t" + "nick" + "\t" + "score");
				System.out.println("===================");
				ArrayList<PlayerDTO> list = dao.rank_dao();

				for (int i = 0; i < list.size(); i++) {
					System.out.print(i + 1 + "위" + "\t");
					System.out.print(list.get(i).getNick() + "\t");
					System.out.print(list.get(i).getScore() + "\t");
					System.out.println();
				}
				System.out.println();

				System.out.println("메인화면으로 돌아가시겠습니까 ? >> Y/N ");
				String answer = sc.next();
				if (answer.equals("Y") || answer.equals("y")) {
					mp3.stop();

					continue;
				}

			} else if (select == 4) {

				System.out.print("프로그램 종료");
				mp3.stop();
				break;

			}

		}

	}
}
