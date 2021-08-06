package dan.morefurnaces;

import dan.morefurnaces.blocks.BlockMoreFurnaces;
import dan.morefurnaces.items.ItemUpgrade;
import dan.morefurnaces.proxy.CommonProxy;
import dan.morefurnaces.proxy.GuiProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(   modid = MoreFurnaces.MODID,
        name = MoreFurnaces.NAME,
        version = MoreFurnaces.VERSION,
        acceptedMinecraftVersions = "[1.12,)",
        useMetadata = true)
public class MoreFurnaces {

    public static final String MODID = "morefurnaces";
    public static final String NAME = "GRADLE:MODNAME";
    public static final String VERSION = "GRADLE:VERSION";

    public static BlockMoreFurnaces BLOCK_FURNACE;
    public static ItemUpgrade ITEM_UPGRADE;

    @SidedProxy(
            clientSide = "dan.morefurnaces.proxy.ClientProxy",
            serverSide = "dan.morefurnaces.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static MoreFurnaces instance;

    public MoreFurnaces() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MFLog.init(event.getModLog());
        Config.init(event.getSuggestedConfigurationFile());
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiProxy());
    }
}
