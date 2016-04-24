import java.awt.Color;
import java.awt.datatransfer.SystemFlavorMap;
import java.awt.event.*;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class BreakoutGame extends GraphicsProgram {
	private static final int SPACE_BETWEEN_BLOCKS = 4;
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HIGHT = 600;

	private static final int NUMBER_OF_COLOMNES = 10;
	private static final int BLOCK_WIDTH = (WINDOW_WIDTH - (NUMBER_OF_COLOMNES - 1)
			* SPACE_BETWEEN_BLOCKS)
			/ NUMBER_OF_COLOMNES;
	private static final int BLOCK_HIGHT = 8;
	private static final int NUMBER_OF_RAWS = 10;
	private static final int BLOCKS_TOP_OFFSET = 70;

	GRect blocks[][] = new GRect[NUMBER_OF_COLOMNES][NUMBER_OF_RAWS];

	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HIGHT = 10;
	private static final int PADDLE_BOTTOM_OFFSET = 30;

	private static final int COLLIDER_RADIUS = 10;

	private static final int SPEED = 4;

	private int blockX = 0;
	private int blockY = BLOCKS_TOP_OFFSET;

	int numberOfBlocks = 0;

	GLabel info = new GLabel("чтобы начать игру, нажмите пробел");
	GLabel info2 = new GLabel("чтобы запустить Шар нажмите пробел");

	int colliderX = (WINDOW_WIDTH - PADDLE_WIDTH) / 2 + PADDLE_WIDTH / 2
			- COLLIDER_RADIUS;
	int colliderY = WINDOW_HIGHT - PADDLE_BOTTOM_OFFSET - COLLIDER_RADIUS * 2;
	GOval collider = new GOval(COLLIDER_RADIUS * 2, COLLIDER_RADIUS * 2);

	RandomGenerator rgen = RandomGenerator.getInstance();
	int randomNumberForCollderDirection = rgen.nextInt(25, 70);
	int colliderDirectionY = (SPEED);
	int colliderDirectionX = (SPEED);

	int paddleX = (WINDOW_WIDTH - PADDLE_WIDTH) / 2;
	int paddleY = WINDOW_HIGHT - PADDLE_BOTTOM_OFFSET;
	GRect paddle = new GRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HIGHT);

	boolean field = false;
	boolean game = false;
	boolean colliderIsOnPaddle = true;

	public void run() {
		addKeyListeners();
		this.setSize(WINDOW_WIDTH, WINDOW_HIGHT);
		addInstruction();
		;
	}

	private void createField() {
		remove(info);
		add(info2, (WINDOW_WIDTH - info.getDescent()) / 4,
				(WINDOW_HIGHT - info.getAscent()) / 2 + info.getAscent());
		setBlocks();
		createCollider();
		createPaddle();
		System.out.println("выставлено " + numberOfBlocks + " блоков");

	}

	private void addInstruction() {
		add(info, (WINDOW_WIDTH - info.getDescent()) / 4,
				(WINDOW_HIGHT - info.getAscent()) / 2);
	}

	private void setBlocks() {
		for (int i = 0; i < NUMBER_OF_COLOMNES; i++) {
			for (int f = 0; f < NUMBER_OF_RAWS / 5; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.RED);
				block.setFilled(true);
				block.setFillColor(Color.RED);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blocks[i][f] = block;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < 2; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.ORANGE);
				block.setFilled(true);
				block.setFillColor(Color.ORANGE);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blocks[i][f] = block;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < 2; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.YELLOW);
				block.setFilled(true);
				block.setFillColor(Color.YELLOW);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blocks[i][f] = block;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < 2; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.GREEN);
				block.setFilled(true);
				block.setFillColor(Color.GREEN);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blocks[i][f] = block;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			for (int f = 0; f < 2; f++) {
				GRect block = new GRect(BLOCK_WIDTH, BLOCK_HIGHT);
				block.setColor(Color.CYAN);
				block.setFilled(true);
				block.setFillColor(Color.CYAN);
				add(block, blockX, blockY);
				numberOfBlocks++;
				blocks[i][f] = block;
				blockY += BLOCK_HIGHT + SPACE_BETWEEN_BLOCKS;
			}
			blockY = BLOCKS_TOP_OFFSET;
			blockX += BLOCK_WIDTH + SPACE_BETWEEN_BLOCKS;
		}
	}

	private void createPaddle() {// создает доску
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLACK);
		add(paddle);
	}

	private void createCollider() {// создает шар
		collider.setFilled(true);
		collider.setFillColor(Color.BLACK);
		add(collider, colliderX, colliderY);
	}

	@Override
	public void keyTyped(KeyEvent e) {// слушатель нажатия клавиш(бессмыслено)

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
		System.out.println("нажата клавиша номер " + e.getKeyCode());// проверяет,
																		// какая
																		// клавиша
																		// нажата(для
																		// удобства
																		// работы
																		// с
																		// кодом)
		if (e.getKeyCode() == 32 && !field) {// проверяет нажатие пробела и
												// выставляет поле
			createField();// создает поле
			field = true;
		} else if (e.getKeyCode() == 32 && field && !game) {// проверяет нажатие пробела
													// и начинает гру
			game = true;
			colliderIsOnPaddle = false;
			remove(info2);
			startGame();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {// управление платформой
		if (field && e.getKeyCode() == 37 && paddle.getX() > 0) {
			paddle.move(-SPEED, 0);
			if (colliderIsOnPaddle == true)
				collider.move(-SPEED, 0);
			pause(SPEED);
		}
		if (field && e.getKeyCode() == 39
				&& paddle.getX() + PADDLE_WIDTH < WINDOW_WIDTH) {
			if (colliderIsOnPaddle == true)
				collider.move(SPEED, 0);
			paddle.move(SPEED, 0);

		}

	}

	private void startGame() {// начинает игру
		int i = 0;
		while (game) {
			moveCollider(i);// запускает Шар
			checkForCollision();// проверяет удар
			checkForBlocksLeft();// проверяет, остались ли блоки
		}
	}

	private void checkForBlocksLeft() {// проверяет на наличие блоков
		if (numberOfBlocks < 1)
			game = false;// завершает игру

	}

	private void checkForCollision() {
		checkForWallCollision();// проверяет столкновение со стенкой по
		checkForBlockCollision();
		// TODO checkForGameEnd();//проверить столкновение с нижней
		// стенкой(смерть)

	}

	private void checkForGameEnd() {
		if (collider.getY() == WINDOW_HIGHT)
			game = false;

	}

	private void checkForBlockCollision() {
		if (getElementAt(collider.getX() + COLLIDER_RADIUS, collider.getY()) != null
				|| getElementAt(collider.getX() + COLLIDER_RADIUS,
						collider.getY() + COLLIDER_RADIUS * 2) != null) {

			for (int i = 0; i < NUMBER_OF_COLOMNES; i++) {
				for (int f = 0; f < NUMBER_OF_RAWS; f++) {
					if (collider.equals(blocks[i][f])) {
						remove(blocks[i][f]);// EXTERMINATE!!
						numberOfBlocks--;
						colliderDirectionX *= -1;
						break;
					}
				}
			}
		} else if (getElementAt(collider.getX(), collider.getY()
				+ COLLIDER_RADIUS) != null
				|| getElementAt(collider.getX() + COLLIDER_RADIUS * 2,
						collider.getY() + COLLIDER_RADIUS) != null) {
			for (int i = 0; i < NUMBER_OF_COLOMNES; i++) {
				for (int f = 0; f < NUMBER_OF_RAWS; f++) {
					if (collider.equals(blocks[i][f])) {
						remove(blocks[i][f]);// EXTERMINATE!
						numberOfBlocks--;//
						colliderDirectionY *= -1;// разворачивает Шар
						break;
					}
				}
			}

		}
	}

	private void checkForWallCollision() {
		if (collider.getY() < 0) {
			colliderDirectionY = -colliderDirectionY;

		}
		if (collider.getX() < 0
				|| collider.getX() + COLLIDER_RADIUS * 2 > WINDOW_WIDTH) {
			colliderDirectionX = -colliderDirectionX;
		}
	}

	private void moveCollider(int i) {// сила, движущая Шар
		collider.move(-colliderDirectionX, -colliderDirectionY);
		pause(400);
		i++;
		System.out.println(i);
		if (i > 0)
			game = false;

	}

}
