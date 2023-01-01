package com.github.game;

import com.github.domain.Poker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerOperation extends Thread {

	//游戏主界面
	GameJFrame gameJFrame;

	//是否能走
	boolean isRun = true;

	//倒计时
	int i;

	public PlayerOperation(GameJFrame m, int i) {
		this.gameJFrame = m;
		this.i = i;
	}

	@Override
	public void run() {
		while (i > -1 && isRun) {
			gameJFrame.time[1].setText("倒计时:" + i--);
			sleep(1);
		}
		if (i == -1){
			gameJFrame.time[1].setText("不抢");
		}
		gameJFrame.landlord[0].setVisible(false);
		gameJFrame.landlord[1].setVisible(false);
		for (Poker poker2 : gameJFrame.playerList.get(1)){
			poker2.setCanClick(true);// 可被点击
		}

		if (gameJFrame.time[1].getText().equals("抢地主")) {
			gameJFrame.playerList.get(1).addAll(gameJFrame.lordList);
			openlord(true);
			sleep(2);
			Common.order(gameJFrame.playerList.get(1));
			Common.rePosition(gameJFrame, gameJFrame.playerList.get(1), 1);
			gameJFrame.publishCard[1].setEnabled(false);
			setlord(1);
		} else {
			if (Common.getScore(gameJFrame.playerList.get(0)) < Common.getScore(gameJFrame.playerList.get(2))) {
				gameJFrame.time[2].setText("抢地主");
				gameJFrame.time[2].setVisible(true);
				setlord(2);
				openlord(true);
				sleep(3);
				gameJFrame.playerList.get(2).addAll(gameJFrame.lordList);
				Common.order(gameJFrame.playerList.get(2));
				Common.rePosition(gameJFrame, gameJFrame.playerList.get(2), 2);
				openlord(false);
			} else {
				gameJFrame.time[0].setText("抢地主");
				gameJFrame.time[0].setVisible(true);
				setlord(0);
				openlord(true);
				sleep(3);
				gameJFrame.playerList.get(0).addAll(gameJFrame.lordList);
				Common.order(gameJFrame.playerList.get(0));
				Common.rePosition(gameJFrame, gameJFrame.playerList.get(0), 0);
				openlord(false);
			}
		}
		gameJFrame.landlord[0].setVisible(false);
		gameJFrame.landlord[1].setVisible(false);
		turnOn(false);
		for (int i = 0; i < 3; i++) {
			gameJFrame.time[i].setText("不要");
			gameJFrame.time[i].setVisible(false);
		}
		gameJFrame.turn = gameJFrame.dizhuFlag;
		while (true) {

			if (gameJFrame.turn == 1) {

				if (gameJFrame.time[0].getText().equals("不要") && gameJFrame.time[2].getText().equals("不要"))
					gameJFrame.publishCard[1].setEnabled(false);
				else {
					gameJFrame.publishCard[1].setEnabled(true);
				}
				turnOn(true);
				timeWait(30, 1);
				turnOn(false);
				gameJFrame.turn = (gameJFrame.turn + 1) % 3;
				if (win())
					break;
			}
			if (gameJFrame.turn == 0) {
				computer0();
				gameJFrame.turn = (gameJFrame.turn + 1) % 3;
				if (win())
					break;
			}
			if (gameJFrame.turn == 2) {
				computer2();
				gameJFrame.turn = (gameJFrame.turn + 1) % 3;
				if (win())
					break;
			}
		}
	}

	//定义一个方法用来暂停N秒
	//参数为等待的时间
	//因为线程中的sleep方法有异常，直接调用影响阅读
	public void sleep(int i) {
		try {
			Thread.sleep(i * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public void openlord(boolean is) {
		for (int i = 0; i < 3; i++) {
			if (is)
				gameJFrame.lordList.get(i).turnFront();
			else {
				gameJFrame.lordList.get(i).turnRear();
			}
			gameJFrame.lordList.get(i).setCanClick(true);
		}
	}

	public void setlord(int i) {
		Point point = new Point();
		if (i == 1) {
			point.x = 80;
			point.y = 430;
			gameJFrame.dizhuFlag = 1;
		}
		if (i == 0) {
			point.x = 80;
			point.y = 20;
			gameJFrame.dizhuFlag = 0;
		}
		if (i == 2) {
			point.x = 700;
			point.y = 20;
			gameJFrame.dizhuFlag = 2;
		}
		gameJFrame.dizhu.setLocation(point);
		gameJFrame.dizhu.setVisible(true);
	}

	public void turnOn(boolean flag) {
		gameJFrame.publishCard[0].setVisible(flag);
		gameJFrame.publishCard[1].setVisible(flag);
	}

	public void computer0() {
		timeWait(1, 0);
		ShowCard(0);

	}

	public void computer2() {
		timeWait(1, 2);
		ShowCard(2);

	}

	public void ShowCard(int role) {
		int orders[] = new int[] { 4, 3, 2, 1, 5 };
		Model model = Common.getModel(gameJFrame.playerList.get(role), orders);
		ArrayList<String> list = new ArrayList<>();
		if (gameJFrame.time[(role + 1) % 3].getText().equals("不要") && gameJFrame.time[(role + 2) % 3].getText().equals("不要")) {
			if (model.a123.size() > 0) {
				list.add(model.a123.get(model.a123.size() - 1));
			} else if (model.a3.size() > 0) {
				if (model.a1.size() > 0) {
					list.add(model.a1.get(model.a1.size() - 1));
				} else if (model.a2.size() > 0) {
					list.add(model.a2.get(model.a2.size() - 1));
				}
				list.add(model.a3.get(model.a3.size() - 1));
			} else if (model.a112233.size() > 0) {
				list.add(model.a112233.get(model.a112233.size() - 1));
			} else if (model.a111222.size() > 0) {
				String name[] = model.a111222.get(0).split(",");

				if (name.length / 3 <= model.a1.size()) {
					list.add(model.a111222.get(model.a111222.size() - 1));
					for (int i = 0; i < name.length / 3; i++)
						list.add(model.a1.get(i));
				} else if (name.length / 3 <= model.a2.size()) {
					list.add(model.a111222.get(model.a111222.size() - 1));
					for (int i = 0; i < name.length / 3; i++)
						list.add(model.a2.get(i));
				}

			} else if (model.a2.size() > (model.a111222.size() * 2 + model.a3.size())) {
				list.add(model.a2.get(model.a2.size() - 1));
			} else if (model.a1.size() > (model.a111222.size() * 2 + model.a3.size())) {
				list.add(model.a1.get(model.a1.size() - 1));
			} else if (model.a4.size() > 0) {
				int sizea1 = model.a1.size();
				int sizea2 = model.a2.size();
				if (sizea1 >= 2) {
					list.add(model.a1.get(sizea1 - 1));
					list.add(model.a1.get(sizea1 - 2));
					list.add(model.a4.get(0));

				} else if (sizea2 >= 2) {
					list.add(model.a2.get(sizea1 - 1));
					list.add(model.a2.get(sizea1 - 2));
					list.add(model.a4.get(0));

				} else {
					list.add(model.a4.get(0));

				}

			}
		} else {

			if (role != gameJFrame.dizhuFlag) {
				int f = 0;
				if (gameJFrame.time[gameJFrame.dizhuFlag].getText().equals("不要")) {
					f = 1;
				}
				if ((role + 1) % 3 == gameJFrame.dizhuFlag) {
					if ((Common.jugdeType(gameJFrame.currentList.get((role + 2) % 3)) != PokerType.c1
							|| Common.jugdeType(gameJFrame.currentList.get((role + 2) % 3)) != PokerType.c2)
							&& gameJFrame.currentList.get(gameJFrame.dizhuFlag).size() < 1)
						f = 1;
					if (gameJFrame.currentList.get((role + 2) % 3).size() > 0
							&& Common.getValue(gameJFrame.currentList.get((role + 2) % 3).get(0)) > 13)
						f = 1;
				}
				if (f == 1) {
					gameJFrame.time[role].setVisible(true);
					gameJFrame.time[role].setText("不要");
					return;
				}
			}

			int can = 0;
			if (role == gameJFrame.dizhuFlag) {
				if (gameJFrame.playerList.get((role + 1) % 3).size() <= 5 || gameJFrame.playerList.get((role + 2) % 3).size() <= 5)
					can = 1;
			} else {
				if (gameJFrame.playerList.get(gameJFrame.dizhuFlag).size() <= 5)
					can = 1;
			}

			ArrayList<Poker> player;
			if (gameJFrame.time[(role + 2) % 3].getText().equals("不要"))
				player = gameJFrame.currentList.get((role + 1) % 3);
			else
				player = gameJFrame.currentList.get((role + 2) % 3);

			PokerType cType = Common.jugdeType(player);

			if (cType == PokerType.c1) {
				if (can == 1)
					model = Common.getModel(gameJFrame.playerList.get(role), new int[] { 1, 4, 3, 2, 5 });
				AI_1(model.a1, player, list, role);
			} else if (cType == PokerType.c2) {
				if (can == 1)
					model = Common.getModel(gameJFrame.playerList.get(role), new int[] { 2, 4, 3, 5, 1 });
				AI_1(model.a2, player, list, role);
			} else if (cType == PokerType.c3) {
				AI_1(model.a3, player, list, role);
			} else if (cType == PokerType.c4) {
				AI_1(model.a4, player, list, role);
			} else if (cType == PokerType.c31) {
				if (can == 1)
					model = Common.getModel(gameJFrame.playerList.get(role), new int[] { 3, 1, 4, 2, 5 });
				AI_2(model.a3, model.a1, player, list, role);
			} else if (cType == PokerType.c32) {
				if (can == 1)
					model = Common.getModel(gameJFrame.playerList.get(role), new int[] { 3, 2, 4, 5, 1 });
				AI_2(model.a3, model.a2, player, list, role);
			} else if (cType == PokerType.c411) {
				AI_5(model.a4, model.a1, player, list, role);
			}

			else if (cType == PokerType.c422) {
				AI_5(model.a4, model.a2, player, list, role);
			}

			else if (cType == PokerType.c123) {
				if (can == 1)
					model = Common.getModel(gameJFrame.playerList.get(role), new int[] { 5, 3, 2, 4, 1 });
				AI_3(model.a123, player, list, role);
			}

			else if (cType == PokerType.c112233) {
				if (can == 1)
					model = Common.getModel(gameJFrame.playerList.get(role), new int[] { 2, 4, 3, 5, 1 });
				AI_3(model.a112233, player, list, role);
			}

			else if (cType == PokerType.c11122234) {
				AI_4(model.a111222, model.a1, player, list, role);
			}

			else if (cType == PokerType.c1112223344) {
				AI_4(model.a111222, model.a2, player, list, role);
			}
			if (list.size() == 0 && can == 1) {
				int len4 = model.a4.size();
				if (len4 > 0)
					list.add(model.a4.get(len4 - 1));
			}

		}

		gameJFrame.currentList.get(role).clear();
		if (list.size() > 0) {
			Point point = new Point();
			if (role == 0)
				point.x = 200;
			if (role == 2)
				point.x = 550;
			if (role == 1) {
				point.x = (770 / 2) - (gameJFrame.currentList.get(1).size() + 1) * 15 / 2;
				point.y = 300;
			}
			point.y = (400 / 2) - (list.size() + 1) * 15 / 2;
			ArrayList<Poker> temp = new ArrayList<>();
			for (int i = 0, len = list.size(); i < len; i++) {
				List<Poker> pokers = getCardByName(gameJFrame.playerList.get(role), list.get(i));
				for (Poker poker : pokers) {
					temp.add(poker);
				}
			}
			temp = Common.getOrder2(temp);
			for (Poker poker : temp) {
				Common.move(poker, poker.getLocation(), point);
				point.y += 15;
				gameJFrame.container.setComponentZOrder(poker, 0);
				gameJFrame.currentList.get(role).add(poker);
				gameJFrame.playerList.get(role).remove(poker);
			}
			Common.rePosition(gameJFrame, gameJFrame.playerList.get(role), role);
		} else {
			gameJFrame.time[role].setVisible(true);
			gameJFrame.time[role].setText("不要");
		}
		for (Poker poker : gameJFrame.currentList.get(role))
			poker.turnFront();
	}

	public List getCardByName(List<Poker> list, String n) {
		String[] name = n.split(",");
		ArrayList cardsList = new ArrayList();
		int j = 0;
		for (int i = 0, len = list.size(); i < len; i++) {
			if (j < name.length && list.get(i).getName().equals(name[j])) {
				cardsList.add(list.get(i));
				i = 0;
				j++;
			}
		}
		return cardsList;
	}

	public void AI_3(List<String> model, List<Poker> player, List<String> list, int role) {

		for (int i = 0, len = model.size(); i < len; i++) {
			String[] s = model.get(i).split(",");
			if (s.length == player.size() && getValueInt(model.get(i)) > Common.getValue(player.get(0))) {
				list.add(model.get(i));
				return;
			}
		}
	}

	public void AI_4(List<String> model1, List<String> model2, List<Poker> player, List<String> list, int role) {
		player = Common.getOrder2(player);
		int len1 = model1.size();
		int len2 = model2.size();

		if (len1 < 1 || len2 < 1)
			return;
		for (int i = 0; i < len1; i++) {
			String[] s = model1.get(i).split(",");
			String[] s2 = model2.get(0).split(",");
			if ((s.length / 3 <= len2) && (s.length * (3 + s2.length) == player.size())
					&& getValueInt(model1.get(i)) > Common.getValue(player.get(0))) {
				list.add(model1.get(i));
				for (int j = 1; j <= s.length / 3; j++)
					list.add(model2.get(len2 - j));
			}
		}
	}

	public void AI_5(List<String> model1, List<String> model2, List<Poker> player, List<String> list, int role) {
		player = Common.getOrder2(player);
		int len1 = model1.size();
		int len2 = model2.size();

		if (len1 < 1 || len2 < 2)
			return;
		for (int i = 0; i < len1; i++) {
			if (getValueInt(model1.get(i)) > Common.getValue(player.get(0))) {
				list.add(model1.get(i));
				for (int j = 1; j <= 2; j++)
					list.add(model2.get(len2 - j));
			}
		}
	}

	public void AI_1(List<String> model, List<Poker> player, List<String> list, int role) {

		for (int len = model.size(), i = len - 1; i >= 0; i--) {
			if (getValueInt(model.get(i)) > Common.getValue(player.get(0))) {
				list.add(model.get(i));
				break;
			}
		}

	}

	public void AI_2(List<String> model1, List<String> model2, List<Poker> player, List<String> list, int role) {
		player = Common.getOrder2(player);
		int len1 = model1.size();
		int len2 = model2.size();
		if (len1 > 0 && model1.get(0).length() < 10) {
			list.add(model1.get(0));
			System.out.println("王炸");
			return;
		}
		if (len1 < 1 || len2 < 1)
			return;
		for (int len = len1, i = len - 1; i >= 0; i--) {
			if (getValueInt(model1.get(i)) > Common.getValue(player.get(0))) {
				list.add(model1.get(i));
				break;
			}
		}
		list.add(model2.get(len2 - 1));
		if (list.size() < 2)
			list.clear();
	}

	public void timeWait(int n, int player) {

		if (gameJFrame.currentList.get(player).size() > 0)
			Common.hideCards(gameJFrame.currentList.get(player));
		if (player == 1) {
			int i = n;

			while (gameJFrame.nextPlayer == false && i >= 0) {
				gameJFrame.time[player].setText("倒计时:" + i);
				gameJFrame.time[player].setVisible(true);
				sleep(1);
				i--;
			}
			if (i == -1 && player == 1) {

				ShowCard(1);
			}
			gameJFrame.nextPlayer = false;
		} else {
			for (int i = n; i >= 0; i--) {
				sleep(1);
				gameJFrame.time[player].setText("倒计时:" + i);
				gameJFrame.time[player].setVisible(true);
			}
		}
		gameJFrame.time[player].setVisible(false);
	}

	public int getValueInt(String n) {
		String name[] = n.split(",");
		String s = name[0];
		int i = Integer.parseInt(s.substring(2, s.length()));
		if (s.substring(0, 1).equals("5"))
			i += 3;
		if (s.substring(2, s.length()).equals("1") || s.substring(2, s.length()).equals("2"))
			i += 13;
		return i;
	}

	public boolean win() {
		for (int i = 0; i < 3; i++) {
			if (gameJFrame.playerList.get(i).size() == 0) {
				String s;
				if (i == 1) {
					s = "恭喜你，胜利了!";
				} else {
					s = "恭喜电脑" + i + ",赢了! 你的智商有待提高哦";
				}
				for (int j = 0; j < gameJFrame.playerList.get((i + 1) % 3).size(); j++)
					gameJFrame.playerList.get((i + 1) % 3).get(j).turnFront();
				for (int j = 0; j < gameJFrame.playerList.get((i + 2) % 3).size(); j++)
					gameJFrame.playerList.get((i + 2) % 3).get(j).turnFront();
				JOptionPane.showMessageDialog(gameJFrame, s);
				return true;
			}
		}
		return false;
	}
}
