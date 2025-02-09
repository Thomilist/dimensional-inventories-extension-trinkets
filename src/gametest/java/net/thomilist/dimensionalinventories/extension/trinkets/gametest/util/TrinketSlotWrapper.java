package net.thomilist.dimensionalinventories.extension.trinkets.gametest.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.test.TestContext;
import net.thomilist.dimensionalinventories.gametest.util.assertion.ItemStackAsserter;
import net.thomilist.dimensionalinventories.util.StringHelper;

public class TrinketSlotWrapper
    extends ItemStackAsserter
{
    private final PlayerEntity player;
    private final String groupKey;
    private final String slotKey;
    private final int index;

    public TrinketSlotWrapper( final TestContext context,
                               final PlayerEntity player,
                               final String groupKey,
                               final String slotKey,
                               final int index )
    {
        super( context );
        this.player = player;
        this.groupKey = groupKey;
        this.slotKey = slotKey;
        this.index = index;
    }

    public TrinketSlotWrapper( final TestContext context,
                               final PlayerEntity player,
                               final String groupKey,
                               final String slotKey )
    {
        this( context, player, groupKey, slotKey, 0 );
    }

    public ItemStack get()
    {
        return TrinketsHelper.get( this.player, this.groupKey, this.slotKey, this.index );
    }

    public void set( final ItemStack itemStack )
    {
        TrinketsHelper.set( this.player, this.groupKey, this.slotKey, itemStack, this.index );
    }

    public String slotName()
    {
        return "trinket slot %s of player %s".formatted(
            StringHelper.joinScopes(
                this.groupKey,
                this.slotKey,
                Integer.toString( this.index )
            ),
            this.player.getDisplayName().getString()
        );
    }

    public void assertItemsEqual( final ItemStack expectedItemStack )
    {
        this.assertItemsEqual( this.get(), expectedItemStack, this.slotName() );
    }

    public void assertEmpty()
    {
        this.assertEmpty( this.get(), this.slotName() );
    }

    public void assertItemType( final Item expectedItem )
    {
        this.assertItemType( this.get(), expectedItem );
    }

    public void assertCount( final int expectedCount )
    {
        this.assertCount( this.get(), expectedCount );
    }

    public void assertDamage( final int expectedDamage )
    {
        this.assertDamage( this.get(), expectedDamage );
    }

    public void assertName( final String expectedName )
    {
        this.assertName( this.get(), expectedName );
    }

    public void assertEnchantment( final Enchantment expectedEnchantment, final int expectedEnchantmentLevel )
    {
        this.assertEnchantment( this.get(), expectedEnchantment, expectedEnchantmentLevel );
    }
}
