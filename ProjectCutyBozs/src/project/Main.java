package project;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		int select = 0;
		// 컨트롤러 객체 생성
		Controller ct = new Controller();
		while (select != 6) {
			System.out.print("[1]회원가입 [2]로그인 [3]랭킹확인 [4]프로그램 종료  >> ");
			select = sc.nextInt();

			if (select == 1) {

				System.out.print("ID를 입력해주세요 >>");
				String id = sc.next();
				System.out.print("Password를 입력해주세요 >>");
				String pw = sc.next();
				System.out.print("nick를 입력해주세요 >>");
				String nick = sc.next();

				PlayerDTO dto = new PlayerDTO(id, pw, nick);
				ct.join(dto);

			} else if (select == 2) {

				System.out.print("ID를 입력해주세요 >>");
				String id = sc.next();
				System.out.print("Password를 입력해주세요 >>");
				String pw = sc.next();

				PlayerDTO dto = new PlayerDTO(id, pw);
				ct.login(dto);
				if(ct.bb == false) {
					break;
				}
				
				

			} else if (select == 4) {

				System.out.print("ㅅㄱ욤");
				break;

			}

		}

	}
}
