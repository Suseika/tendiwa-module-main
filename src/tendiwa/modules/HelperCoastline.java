package tendiwa.modules;

import tendiwa.core.*;
import tendiwa.locationFeatures.FeatureOcean;
import tendiwa.resources.FloorTypes;

public enum HelperCoastline implements LocationHelper {
	INSTANCE;

@Override
public void draw(Location location, LocationPlace place) {
	location.square(5, 5, 5, 5, FloorTypes.water);
	// Transition to oceans
	int diffusionRadius = 10;
	for (LocationNeighborship neighborship : place.getNeighborships()) {
		boolean foundOcean = false;
		for (LocationFeature feature : neighborship.getPlace().getFeatures()) {
			if (feature instanceof FeatureOcean) {
				foundOcean = true;
				break;
			}
		}
		if (!foundOcean) {
			continue;
		}
		if (neighborship.getLength() <= diffusionRadius * 2) {
			continue;
		}
		EnhancedRectangle borderRectangle = place
			.getRectangleInFrontOfNeighbor(neighborship, diffusionRadius);
		location.transitionBuilder()
			.setRectangle(borderRectangle)
			.setDepth(diffusionRadius)
			.addFromDirection(neighborship.getSide())
			.setFrom(FloorTypes.water)
			.build();

	}
}
}