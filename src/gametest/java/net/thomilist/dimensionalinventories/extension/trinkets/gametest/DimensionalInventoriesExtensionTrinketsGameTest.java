package net.thomilist.dimensionalinventories.extension.trinkets.gametest;

import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;

public class DimensionalInventoriesExtensionTrinketsGameTest
{
    @GameTest( templateName = FabricGameTest.EMPTY_STRUCTURE )
    public void dummyTest( final TestContext context )
    {
        context.complete();
    }
}
