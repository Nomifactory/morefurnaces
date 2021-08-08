package dan.morefurnaces.proxy;

import dan.morefurnaces.MoreFurnaces;
import dan.morefurnaces.items.Upgrades;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        Item item = Item.getItemFromBlock(MoreFurnaces.BLOCK_FURNACE);

        String[] suffixes = {"iron", "gold", "diamond", "obsidian", "netherrack", "copper", "silver"};
        for (int i = 0; i < suffixes.length; i++) {
            ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation("morefurnaces:furnace_" + suffixes[i], "inventory"));
        }

        for (Upgrades upgrade : Upgrades.values()) {
            ModelResourceLocation l = new ModelResourceLocation("morefurnaces:upgrade_" + upgrade.getUnlocalizedName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(MoreFurnaces.ITEM_UPGRADE, upgrade.ordinal(), l);
        }
    }
}
