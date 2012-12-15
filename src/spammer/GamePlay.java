package spammer;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import backend.GameComponentMap;
import backend.ui.IActionListener;
import backend.ui.PushButton;
import backend.ui.RootPane;
import backend.ui.UIBasicGameState;
import backend.ui.Widget;

public class GamePlay extends UIBasicGameState {
	private static final int COEF_SCORE = 1;
	private static final long END_TIME = 120; // seconds
	private ArrayList<Character> characters;
	private FireWall fireWall;
	private int score = 0;
	private long timeBeforeEnd; // milliseconds
	private GameComponentMap gcm;

	@Override
	public void init(GameContainer gc, StateBasedGame stg)
			throws SlickException {
		characters = new ArrayList<Character>();
		Character c1 = new Character(0);
		Character c2 = new Character(1);
		Character c3 = new Character(2);
		Character c4 = new Character(3);
		characters.add(c1);
		characters.add(c2);
		characters.add(c3);
		characters.add(c4);
		fireWall = new FireWall();
		gcm = new GameComponentMap();
		gcm.stageComponent(fireWall);
		gcm.stageComponent(c1);
		gcm.stageComponent(c2);
		gcm.stageComponent(c3);
		gcm.stageComponent(c4);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		timeBeforeEnd = END_TIME * 1000;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		gcm.renderAll(gc, sbg, g);

		g.setColor(Color.red);
		g.drawString("Score : " + score, 50, 50);

		RenderTimer.draw(gc, g, (float) timeBeforeEnd / 1000.f);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException 
	{
		timeBeforeEnd -= delta;
		
		
		gcm.updateAll(gc, sbg, delta);
	}

	@Override
	public int getID() {
		return MainGame.GAME_PLAY;
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException {
		ui = new RootPane(container.getWidth(), container.getHeight()); // ecran

		// Champ pour le texte
		final SpammerTextField field = new SpammerTextField(ui, 500, 32);
		field.setPosition(50, 550);
		field.addValidateListener(new IActionListener() {

			@Override
			public void actionPerformed(Widget sender) {
				sendMessage(field, field.getText());
			}

		});
		ui.add(field);

		// Bouton envoyer
		PushButton btn = new PushButton(ui, 610, 550, 50, 32, "SPAM");
		btn.addActionListener(new IActionListener() {
			@Override
			public void actionPerformed(Widget sender) {
				sendMessage(field, field.getText());
			}
		});
		ui.add(btn);

	}

	public void sendMessage(SpammerTextField field, String word) {

		if (fireWall.contains(word)) {
			System.out.println("BLOCKED");
		}

		else
			for (Character c : characters) {
				if (c.isInInterest(word)) {
					score += COEF_SCORE;
					gcm.stageComponent(new Mail(c.getIDCharacter()));
					System.out.println(score);
				}
			}

		fireWall.add(word);
		field.setText("");
	}

}
