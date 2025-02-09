package net.thomilist.dimensionalinventories.extension.trinkets.gametest;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.test.AfterBatch;
import net.thomilist.dimensionalinventories.gametest.util.TestState;

public class Batches
{
    public static final String TRINKETS = "trinkets";

    @AfterBatch( batchId = Batches.TRINKETS )
    public void stashAfterTrinkets( final ServerWorld unused )
    {
        TestState.stashModData( Batches.TRINKETS );
    }
}
