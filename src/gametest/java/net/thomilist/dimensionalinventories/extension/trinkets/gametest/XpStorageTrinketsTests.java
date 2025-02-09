package net.thomilist.dimensionalinventories.extension.trinkets.gametest;

import com.github.charlyb01.xpstorage_trinkets.XpstorageTrinkets;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.item.ItemStack;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;
import net.thomilist.dimensionalinventories.extension.trinkets.gametest.util.TrinketSlotWrapper;
import net.thomilist.dimensionalinventories.extension.trinkets.module.TrinketsModuleGroup;
import net.thomilist.dimensionalinventories.gametest.DimensionalInventoriesGameTest;
import net.thomilist.dimensionalinventories.gametest.util.BasicModSetup;

/**
 * Compatibility tests with <a href="https://github.com/sf-inc/xp_storage_trinkets">XP Storage Trinkets</a>.
 */
public class XpStorageTrinketsTests
    extends DimensionalInventoriesGameTest
{
    @GameTest( templateName = FabricGameTest.EMPTY_STRUCTURE,
               batchId = Batches.TRINKETS )
    public void transitionSwapsXpStorageTrinkets( final TestContext context )
    {
        this.logTestStart();

        final BasicModSetup setup = BasicModSetup.withDefaultAndAdditionalModules( new TrinketsModuleGroup() );
        final FakePlayer player = FakePlayer.get( context.getWorld() );

        // The XP Storage trinkets can be equipped in the "hand/ring", "offhand/ring" and "chest/necklace" slots
        final TrinketSlotWrapper handSlot = new TrinketSlotWrapper( context, player, "hand", "ring" );
        final TrinketSlotWrapper offHandSlot = new TrinketSlotWrapper( context, player, "offhand", "ring" );
        final TrinketSlotWrapper necklaceSlot = new TrinketSlotWrapper( context, player, "chest", "necklace" );

        // Nothing done yet; all slots should be empty
        handSlot.assertEmpty();
        offHandSlot.assertEmpty();
        necklaceSlot.assertEmpty();

        // XP Conduits can be placed in either ring slot. Create one for each
        final ItemStack xpConduit1 = new ItemStack( XpstorageTrinkets.xp_conduit, 1 );
        final ItemStack xpConduit2 = new ItemStack( XpstorageTrinkets.xp_conduit, 1 );

        // XP Savers can only be placed in the necklace slot. Create one for that
        final ItemStack xpSaver = new ItemStack( XpstorageTrinkets.xp_saver, 1 );

        // Equip the XP Storage trinkets
        handSlot.set( xpConduit1 );
        offHandSlot.set( xpConduit2 );
        necklaceSlot.set( xpSaver );

        // Make sure the trinkets are actually equipped
        handSlot.assertItemType( XpstorageTrinkets.xp_conduit );
        handSlot.assertCount( 1 );
        offHandSlot.assertItemType( XpstorageTrinkets.xp_conduit );
        offHandSlot.assertCount( 1 );
        necklaceSlot.assertItemType( XpstorageTrinkets.xp_saver );
        necklaceSlot.assertCount( 1 );

        // Travel to a different dimension pool
        setup.instance.transitionHandler.handlePlayerDimensionChange(
            player,
            BasicModSetup.ORIGIN_DIMENSION,
            BasicModSetup.DESTINATION_DIMENSION
        );

        // The XP Storage trinkets should be left behind in the original dimension pool
        handSlot.assertEmpty();
        offHandSlot.assertEmpty();
        necklaceSlot.assertEmpty();

        // Return to the original dimension pool
        setup.instance.transitionHandler.handlePlayerDimensionChange(
            player,
            BasicModSetup.DESTINATION_DIMENSION,
            BasicModSetup.ORIGIN_DIMENSION
        );

        // The XP Storage trinkets should now be back
        handSlot.assertItemType( XpstorageTrinkets.xp_conduit );
        handSlot.assertCount( 1 );
        offHandSlot.assertItemType( XpstorageTrinkets.xp_conduit );
        offHandSlot.assertCount( 1 );
        necklaceSlot.assertItemType( XpstorageTrinkets.xp_saver );
        necklaceSlot.assertCount( 1 );

        context.complete();
    }
}
