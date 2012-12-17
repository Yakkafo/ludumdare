package spammer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import spammer.content.Sounds;

import backend.ui.UIBasicGameState;

public class IntroScreen extends UIBasicGameState
{
	private int imageIndex;
	private Image images[];
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		String dir = "assets/";
		images = new Image[3];
		images[0] = new Image(dir + "screen_start.png");
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		System.out.println("Entering intro...");
		super.enter(container, game);
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException
	{
		System.out.println("Leaving intro...");
		super.leave(container, game);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics gfx)
			throws SlickException
	{
		gfx.setColor(Color.green);
		gfx.pushTransform();
		float t = (float)(System.currentTimeMillis() % 1000) / 10.f;
		gfx.translate(50, 50);
		gfx.rotate(0, 0, t);
		gfx.fillRect(0, 0, 100, 50);
		gfx.popTransform();
		
		if(imageIndex < images.length)
		{
			Image i = images[imageIndex];
			if(images[imageIndex] != null)
			{
				// Draws the image centered
				gfx.drawImage(i, 
						(i.getWidth() - container.getWidth()) / 2, 
						(i.getHeight() - container.getHeight()) / 2);
			}
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException
	{
		if(imageIndex >= images.length)
		{
//			if(container.getInput().isKeyDown(Input.KEY_SPACE))
				game.enterState(MainGame.GAME_PLAY);
		}
		else if(images[imageIndex] == null)
			imageIndex++;
	}
	
	@Override
	public void keyPressed(int key, char c)
	{
		super.keyPressed(key, c);
		if(key == Input.KEY_SPACE || key == Input.KEY_ENTER)
			imageIndex++;
	}
	
	@Override
	public void mousePressed(int button, int x, int y)
	{
		super.mousePressed(button, x, y);
		imageIndex++;
	}

	@Override
	protected void createUI(GameContainer container, StateBasedGame game)
			throws SlickException
	{
	}

	@Override
	public int getID()
	{
		return MainGame.INTRO_SCREEN;
	}

}


