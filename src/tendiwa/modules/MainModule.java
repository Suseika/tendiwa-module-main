package tendiwa.modules;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyShell;
import org.tendiwa.entities.Spells;
import tendiwa.core.*;
import tendiwa.drawing.*;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class MainModule extends Module implements WorldProvider {

public MainModule() {
	DefaultDrawingAlgorithms.register(EnhancedRectangle.class, DrawingRectangle.withColorLoop(Color.GRAY, Color.BLACK, Color.BLUE));
	DefaultDrawingAlgorithms.register(RectangleSystem.class, DrawingRectangleSystem
		.withColors(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW));
	DefaultDrawingAlgorithms.register(
		RectangleSidePiece.class,
		DrawingRectangleSidePiece.withColor(Color.MAGENTA));
	DefaultDrawingAlgorithms.register(Segment.class, DrawingSegment.withColor(Color.BLUE));
	DefaultDrawingAlgorithms.register(Chunk.class, DrawingTerrain.defaultAlgorithm());
	DefaultDrawingAlgorithms.register(World.class, DrawingWorld.level(0));

//	ResourcesRegistry.registerDrawer(new TestLocationDrawer());
	ResourcesRegistry.registerDrawer(new BuildingsLocationDrawer());
//	ResourcesRegistry.registerDrawer(new Forest());
	ResourcesRegistry.registerDrawer(new Ocean());
}

public static void main(String[] args) {
	Tendiwa.initWithDummyClient();
	MainModule mainModule = new MainModule();
//	TestCanvas canvas = canvas(2);
	Tendiwa.createWorld(mainModule);
//	canvas.draw(Tendiwa.getWorld());
	mainModule.testScript();
}

@Override
public World createWorld() {
	World world = World.create(new SuseikaWorld(), 400, 300);
//	Character playerCharacter = world.createPlayerCharacter(120, 130, CharacterTypes.human, "Suseika");
//	world.setPlayerCharacter(playerCharacter);

//	world.createCharacter(125, 131, CharacterTypes.bear, "mishka");
//	world.createCharacter(125, 132, CharacterTypes.bear, "mishka");
//	playerCharacter.getItem(ItemsTypes.shortBow);
//	playerCharacter.getItem(ItemsTypes.shortBow);
//	playerCharacter.getItem(ItemsTypes.shortBow);
//	playerCharacter.getItem(ItemsTypes.shortBow);
//	playerCharacter.getItem(ItemsTypes.shortBow);
//	playerCharacter.getItem(ItemsTypes.shortBow);

//	playerCharacter.getItem(ItemsTypes.shortBow);
//	playerCharacter.getItem(ItemsTypes.ironArmor);
//	playerCharacter.getItem(ItemsTypes.woodenArrow, 10);
//	playerCharacter.getItem(ItemsTypes.ironHelm);
//	playerCharacter.learnSpell(Spells.FIREBALL);
//	playerCharacter.learnSpell(Spells.BLINK);
//	playerCharacter.wield(playerCharacter.getInventory().getItem(new Condition<Item>() {
//		@Override
//		public boolean check(Item item) {
//			return item.getType() == ItemsTypes.shortBow;
//		}
//	}));
	return world;
}

private void testScript() {
	ClassLoader parentCl = MainModule.class.getClassLoader();
	GroovyClassLoader gcl = new GroovyClassLoader(parentCl);
	Class aClass;
	URL resource;
	resource = MainModule.class.getResource("/Test.groovy");
	try {
		Binding binding = new Binding();
		binding.setVariable("penis", Spells.BLINK);
		new GroovyShell(binding).evaluate(new GroovyCodeSource(resource));
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
