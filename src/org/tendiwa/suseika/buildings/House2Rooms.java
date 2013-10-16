package org.tendiwa.suseika.buildings;

import tendiwa.core.Building;
import tendiwa.core.StaticData;
import tendiwa.core.meta.Coordinate;
import tendiwa.core.terrain.settlements.BuildingPlace;
import tendiwa.core.CardinalDirection;
import tendiwa.core.EnhancedRectangle;
import tendiwa.core.RectangleSystem;
import tendiwa.resources.FloorTypes;
import tendiwa.resources.ObjectTypes;

public class House2Rooms extends Building {
	public static final long serialVersionUID = 82134511L;
	public House2Rooms(BuildingPlace bp, CardinalDirection side) {
		super(bp, side);
	}
	@Override
	public void draw() {
		int wallGreyStone = StaticData.getObjectType("wall_gray_stone").getId();
		int objDoorBlue = StaticData.getObjectType("door_blue").getId();

		RectangleSystem crs = new RectangleSystem(1);
		CardinalDirection side = CardinalDirection.S;
		EnhancedRectangle hallToKitchen = crs.addRectangle(new EnhancedRectangle(x, y, width, height));
		EnhancedRectangle rightRoom = crs.cutRectangleFromSide(hallToKitchen, side.counterClockwiseQuarter(), 5);
		EnhancedRectangle hall = crs.cutRectangleFromSide(hallToKitchen, side, 2);
		EnhancedRectangle middleRoom = crs.cutRectangleFromSide(hallToKitchen, side.counterClockwiseQuarter(), 5);
		EnhancedRectangle exPartOfStoreroom = crs.cutRectangleFromSide(hall, side.clockwiseQuarter(), 2);
		// int kitchen = crs.cutRectangleFromSide(0, side.opposite(), 5);
		EnhancedRectangle storeroom = crs.cutRectangleFromSide(hallToKitchen, side.clockwiseQuarter(), 2);

		// Stretch storeroom
		storeroom.stretch(side, exPartOfStoreroom.getDimensionBySide(side) + 1);
		crs.excludeRectangle(exPartOfStoreroom);

		terrainModifier = settlement.getTerrainModifier(crs);
		buildBasis(FloorTypes.stone, ObjectTypes.wall_grey_stone);

		// Remove walls of hall to kitchen
		removeWall(hallToKitchen, side);
		removeWall(hallToKitchen, side.opposite());

		// Doors
		// Storeroom
		Coordinate c = storeroom.getCellFromSide(side.counterClockwiseQuarter(), side, 1).moveToSide(side.counterClockwiseQuarter(), 1);
		settlement.setObject(c.x, c.y, objDoorBlue);
		// Middle room
		c = middleRoom.getMiddleOfSide(side).moveToSide(side, 1);
		settlement.setObject(c.x, c.y, objDoorBlue);
		// Right room
		c = rightRoom.getCellFromSide(side.clockwiseQuarter(), side, 0).moveToSide(side.clockwiseQuarter(), 1);
		settlement.setObject(c.x, c.y, objDoorBlue);
		// Front Door
		placeFrontDoor(hall, side);
	}
	@Override
	public boolean fitsToPlace(BuildingPlace place) {
		return true;
	}
}
