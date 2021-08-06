package dan.morefurnaces.proxy;

import dan.morefurnaces.FurnaceType;
import dan.morefurnaces.MoreFurnaces;
import dan.morefurnaces.blocks.BlockMoreFurnaces;
import dan.morefurnaces.items.ItemMoreFurnaces;
import dan.morefurnaces.items.ItemUpgrade;
import dan.morefurnaces.recipes.Recipes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = MoreFurnaces.MODID)
public class CommonProxy {

    public void preInit() {
        MoreFurnaces.BLOCK_FURNACE = new BlockMoreFurnaces();
        MoreFurnaces.ITEM_UPGRADE = new ItemUpgrade();
    }

    public void init() {
        for (FurnaceType type : FurnaceType.values()) {
            GameRegistry.registerTileEntity(type.clazz, "CubeX2 " + type.friendlyName);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();

        registry.register(MoreFurnaces.BLOCK_FURNACE);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();

        // Items
        registry.register(MoreFurnaces.ITEM_UPGRADE);

        // ItemBlocks
        registry.register(createItemBlock(MoreFurnaces.BLOCK_FURNACE, ItemMoreFurnaces::new));
    }

    //@SubscribeEvent
    //public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
    //    Recipes.registerFurnaceRecipes();
    //    Recipes.registerUpgradeRecipes();
    //}

    private static <T extends Block> ItemBlock createItemBlock(T block, Function<T, ItemBlock> producer) {
        ItemBlock itemBlock = producer.apply(block);
        //noinspection ConstantConditions
        itemBlock.setRegistryName(block.getRegistryName());
        return itemBlock;
    }
}
