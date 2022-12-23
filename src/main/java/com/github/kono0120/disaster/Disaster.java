package com.github.kono0120.disaster;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public final class Disaster extends JavaPlugin implements Listener {
    private static Disaster plugin;
    private static List<World> worldList = Bukkit.getWorlds();

    public static Disaster getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        worldList.forEach(world -> world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false));
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent deathEvent) {

        //デスしたプレイヤーの取得
        Player player = deathEvent.getEntity();

        //災害を起こす

        Random rand = new Random();
        // 1~10
        int num = rand.nextInt(10) + 1;

       switch (num) {
            case 1:
                thunder(player);
                // メソッド1の呼び出し
                break;
            case 2:
                MAKIMA(player);
                // メソッド2の呼び出し
                break;
            case 3:
                MAGMA(player);
                // メソッド3の呼び出し
                break;
            case 4:
                WATER(player);
                // メソッド4の呼び出し
                break;
            case 5:
                TNT(player);
                // メソッド5の呼び出し
                break;
            case 6:
                explosion(player);
                // メソッド6の呼び出し
                break;
            case 7:
                TENSE();
                // メソッド7の呼び出し
                break;
            case 8:
                RAIN();
                // メソッド8の呼び出し
                break;
            case 9:
                NIGHT();
                // メソッド9の呼び出し
                break;
            case 10:
                NIGHT();
                // メソッド10の呼び出し
                break;
            case 11:
                JUMP(player);
                // メソッド11の呼び出し
                break;
            case 12:
                onExplosionPrime(player) ;
                // メソッド12の呼び出し
                break;
            // 以下10まで続ける。
        }


    }

    void thunder(Player player) {
        //Playerに雷を落とす
        player.getLocation().getWorld().strikeLightning(player.getLocation());

    }

    void MAKIMA(Player player) {
        player.sendMessage("【悲報】最終決戦後、デンジはマキマを普通に殺すのではなく、彼女の肉を調理して食べるという選択をとった");
    }

    void MAGMA(Player player) {
        Random rand = new Random();
        int num = rand.nextInt(5) + 1;
        player.getLocation().add(num, num, num).getBlock().setType(Material.LAVA);

    }

    void WATER(Player player) {
        Random rand = new Random();
        int num = rand.nextInt(5) + 1;
        player.getLocation().add(num, num, num).getBlock().setType(Material.WATER);

    }

    void TNT(Player player) {
        //位置を設定しただけで、数の設定の仕方がわからない
        Random rand = new Random();
        int num = rand.nextInt(5) + 1;
        player.getLocation().add(num, num, num).getBlock().setType(Material.TNT);

    }

    void explosion(Player player) {
        //Playerの位置で爆発する
        Random rand = new Random();
        int num = rand.nextInt(10) + 1;
        player.getLocation().getWorld().createExplosion(player.getLocation(), num);


    }

    void mob(Player player) {
        //ゾンビmobが10体湧く
        for (int i = 0; i < 10; i++) {
            player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
        }
    }

    void TENSE() {

        //陰キャ転生がkickされる
        Player INKYA = Bukkit.getPlayer("INKYATENSEI");
        if (INKYA != null) {
            INKYA.kickPlayer("アサ「あのさ...私キチンとマイクラを楽しめる計画を立ててきてるの」");
        }

        //ひまじんがkickされる
        Player HIMA = Bukkit.getPlayer("H1maz1n1");
        if (HIMA != null) {
            HIMA.kickPlayer("メン限。ひまじんさん、10分後行っていいよ");
        }

        //できおこがkickされる
        Player DEKI = Bukkit.getPlayer("Dekioko");
        if (DEKI != null) {
            DEKI.kickPlayer("ぼっちだけどロックしてねぇよおまえは");
        }

        //こうたんがkickされる
        Player KOUTA = Bukkit.getPlayer("God_koutan");
        if (KOUTA != null) {
            KOUTA.kickPlayer("こうたん「自分で抜いた場合、すっごい量が少ない」");
        }

        //なっしーがkickされる
        Player SUTOKA = Bukkit.getPlayer("Nassy_74");
        if (SUTOKA != null) {
            SUTOKA.kickPlayer("ウイルスに感染しています。早急の対応が必要です");
        }
    }

    void RAIN() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather rain");
    }

    void NIGHT() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "time set night");
    }

    void JUMP(Player player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tp @r[limit=5] 0 200 0");
    }

    private void onExplosionPrime(Player player) {
        Location center = player.getLocation();
        World world = center.getWorld();
        int totalTick = 60;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Set<Entity> involvedEntities = Collections.synchronizedSet(new HashSet<>());
        new BukkitRunnable() {
            @Override
            public void run() {
                // ※ブラックホールのサイズ　好きな数字を入れる
                int radius = 5;
                Set<Block> blockSet = new HashSet<>();

                for (int i = 0; i < radius; i++) {
                    getBlockSphereAround(center, i).forEach(block -> {
                        blockSet.add(block);
                    });
                }

                blockSet.forEach(x -> {

                    if (x.isEmpty()) {
                        return;
                    }

                    if (random.nextDouble() <= 0.05) {
                        FallingBlock fallingBlock = world.spawnFallingBlock(x.getLocation(),
                                x.getBlockData());
                        fallingBlock.setGravity(false);
                        fallingBlock.setInvulnerable(true);
                        involvedEntities.add(fallingBlock);
                    }
                    x.setType(Material.AIR);

                });
                Bukkit.selectEntities(Bukkit.getConsoleSender(), "@e").stream()
                        .filter(x -> x.getLocation().getWorld().equals(center.getWorld()))
                        .filter(x -> x.getLocation().distance(center) <= radius)
                        .filter(x -> {
                            if (x instanceof Player) {
                                Player p = ((Player) x);
                                return p.getGameMode() == GameMode.SURVIVAL
                                        || p.getGameMode() == GameMode.ADVENTURE;
                            }
                            return true;
                        })
                        .forEach(involvedEntities::add);
            }
        }.runTask(this);

        new BukkitRunnable() {
            private int tick = 0;

            @Override
            public void run() {
                world.playSound(center, Sound.ENTITY_WITHER_SPAWN, 1, 1);
                involvedEntities.forEach(x -> {
                    Vector sub;
                    try {
                        sub = center.toVector().subtract(x.getLocation().toVector());
                        sub.multiply(0.35 / sub.length());
                    } catch (IllegalArgumentException e) {
                        return;
                    }

                    x.setVelocity(sub);

                    if (x.getLocation().toVector().distance(center.toVector()) < 1) {
                        if (x instanceof FallingBlock) {
                            x.remove();
                        }
                        if (x instanceof LivingEntity) {
                            ((LivingEntity) x).damage(1000);
                        }
                    }
                    center.getWorld().spawnParticle(Particle.REDSTONE, x.getLocation(), 3,
                            new Particle.DustOptions(Color.BLACK, 5));

                });

                tick++;
                if (tick >= totalTick) {
                    involvedEntities.stream()
                            .filter(x -> x instanceof FallingBlock)
                            .forEach(Entity::remove);
                    cancel();
                }
            }
        }.runTaskTimer(this, 0, 1);
    }

    public static Set<Block> getBlockSphereAround(Location location, int radius) {
        Set<Block> sphere = new HashSet<>();
        Block center = location.getBlock();
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block b = center.getRelative(x, y, z);

                    if (center.getLocation().distance(b.getLocation()) <= radius) {
                        sphere.add(b);
                    }
                }
            }
        }
        return sphere;
    }
}
