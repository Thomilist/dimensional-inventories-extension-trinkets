package net.thomilist.dimensionalinventories.extension.trinkets.gametest;

import net.fabricmc.fabric.api.entity.FakePlayer;
import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;
import net.minecraft.text.Text;
import net.thomilist.dimensionalinventories.extension.trinkets.gametest.util.TrinketSlotWrapper;
import net.thomilist.dimensionalinventories.extension.trinkets.module.TrinketsModuleGroup;
import net.thomilist.dimensionalinventories.gametest.DimensionalInventoriesGameTest;
import net.thomilist.dimensionalinventories.gametest.util.BasicModSetup;

/**
 * Compatibility tests with <a href="https://github.com/illusivesoulworks/elytraslot">Elytra Slot</a>.
 */
public class ElytraSlotTests
    extends DimensionalInventoriesGameTest
{
    @GameTest( templateName = FabricGameTest.EMPTY_STRUCTURE,
               batchId = Batches.TRINKETS )
    public void transitionSwapsElytraSlot( final TestContext context )
    {
        this.logTestStart();

        final BasicModSetup setup = BasicModSetup.withDefaultAndAdditionalModules( new TrinketsModuleGroup() );
        final FakePlayer player = FakePlayer.get( context.getWorld() );

        // The "elytra slot" is implemented as the "chest/cape" slot
        final TrinketSlotWrapper capeSlot = new TrinketSlotWrapper( context, player, "chest", "cape" );

        // Nothing done yet; slot should be empty
        capeSlot.assertEmpty();

        // Create elytra item with various components
        final ItemStack elytra = new ItemStack( Items.ELYTRA );
        elytra.setCount( 1 );
        elytra.setDamage( 30 );
        elytra.setCustomName( Text.of( "Winging it" ) );
        elytra.addEnchantment( Enchantments.UNBREAKING, 3 );
        elytra.addEnchantment( Enchantments.MENDING, 1 );

        // Equip the elytra in the trinket slot
        capeSlot.set( elytra );

        // Make sure the elytra is actually equipped
        capeSlot.assertItemType( Items.ELYTRA );
        capeSlot.assertCount( 1 );
        capeSlot.assertDamage( 30 );
        capeSlot.assertName( "Winging it" );
        capeSlot.assertEnchantment( Enchantments.UNBREAKING, 3 );
        capeSlot.assertEnchantment( Enchantments.MENDING, 1 );

        // Travel to a different dimension pool
        setup.instance.transitionHandler.handlePlayerDimensionChange(
            player,
            BasicModSetup.ORIGIN_DIMENSION,
            BasicModSetup.DESTINATION_DIMENSION
        );

        // The elytra should be left behind in the original dimension pool
        capeSlot.assertEmpty();

        // Return to the original dimension pool
        setup.instance.transitionHandler.handlePlayerDimensionChange(
            player,
            BasicModSetup.DESTINATION_DIMENSION,
            BasicModSetup.ORIGIN_DIMENSION
        );

        // The elytra should now be back
        capeSlot.assertItemType( Items.ELYTRA );
        capeSlot.assertCount( 1 );
        capeSlot.assertDamage( 30 );
        capeSlot.assertName( "Winging it" );
        capeSlot.assertEnchantment( Enchantments.UNBREAKING, 3 );
        capeSlot.assertEnchantment( Enchantments.MENDING, 1 );

        context.complete();
    }
}
