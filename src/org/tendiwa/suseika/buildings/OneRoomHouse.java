package org.tendiwa.suseika.buildings;

import tendiwa.core.Building;
import tendiwa.core.StaticData;
import tendiwa.core.terrain.settlements.BuildingPlace;
import tendiwa.geometry.CardinalDirection;
import tendiwa.resources.FloorTypes;
import tendiwa.resources.ObjectTypes;

public class OneRoomHouse extends Building {
	public static final long serialVersionUID = 35681734L;
	public OneRoomHouse(BuildingPlace bp, CardinalDirection side) {
		super(bp, side);
	}

	public void draw() {
		int wallWoorden = StaticData.getObjectType("wall_wooden").getId();
		
		getTerrainModifier(900);	
		buildBasis(FloorTypes.stone, ObjectTypes.wall_wooden);
		
		placeFrontDoor(getDoorSide());
	}
	@Override
	public boolean fitsToPlace(BuildingPlace place) {
		// TODO Auto-generated method stub
		return place.width > 6 || place.height > 6;
	}
}
