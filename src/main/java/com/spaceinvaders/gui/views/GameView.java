package com.spaceinvaders.gui.views;

import com.spaceinvaders.gui.GameManager;
import com.spaceinvaders.states.GameContext;
import com.spaceinvaders.utils.GameLogger;
import com.spaceinvaders.entities.*;
import com.spaceinvaders.composite.*;
import com.spaceinvaders.factory.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class GameView {
    private Pane view;
    private Canvas canvas;
    private GraphicsContext gc;
    private GameManager gameManager;
    private GameContext gameContext;
    private AnimationTimer gameLoop;
    
    // Variables pour le joueur et le mouvement
    private double playerX = 400;
    private double playerY = 500;
    private final double playerSpeed = 5;
    private Set<KeyCode> activeKeys = new HashSet<>();
    
    // Variables pour les ennemis
    private Enemy[] enemies;
    private double[] enemyX;
    private double[] enemyY;
    private double enemySpeed = 1;
    private boolean movingRight = true;
    
    // Système de projectiles amélioré
    private List<Projectile> projectiles = new ArrayList<>();
    private long lastShotTime = 0;
    private final long SHOT_COOLDOWN = 200; // ms entre les tirs

    // Classe interne pour gérer les projectiles
    private class Projectile {
        double x, y;
        double speed = 8;
        boolean active = true;
        
        Projectile(double startX, double startY) {
            this.x = startX;
            this.y = startY;
        }
        
        void update() {
            y -= speed; // Se déplace vers le haut
            if (y < 0) active = false; // Désactive si hors écran
        }
        
        void draw(GraphicsContext gc) {
            gc.setFill(Color.YELLOW);
            gc.fillRect(x - 2, y - 10, 4, 15);
            gc.setFill(Color.ORANGE);
            gc.fillRect(x - 1, y - 8, 2, 12);
        }
    }

    public GameView(GameManager gameManager, GameContext gameContext) {
        this.gameManager = gameManager;
        this.gameContext = gameContext;
        initializeEnemies();
        createView();
        startGameLoop();
        setupInputHandlers();
    }

    private void initializeEnemies() {
        // Création d'ennemis via Factory Pattern
        EnemyFactory basicFactory = EnemyFactoryProducer.getFactory(EnemyType.BASIC);
        EnemyFactory fastFactory = EnemyFactoryProducer.getFactory(EnemyType.FAST);
        EnemyFactory tankFactory = EnemyFactoryProducer.getFactory(EnemyType.TANK);
        
        enemies = new Enemy[8];
        enemyX = new double[8];
        enemyY = new double[8];
        
        // Positionnement des ennemis en formation
        for (int i = 0; i < 8; i++) {
            if (i < 3) {
                enemies[i] = basicFactory.createEnemy("BasicAlien" + i);
            } else if (i < 6) {
                enemies[i] = fastFactory.createEnemy("FastAlien" + i);
            } else {
                enemies[i] = tankFactory.createEnemy("TankAlien" + i);
            }
            
            enemyX[i] = 150 + (i % 4) * 150;
            enemyY[i] = 100 + (i / 4) * 80;
        }
    }

    private void createView() {
        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        
        view = new Pane(canvas);
        view.setStyle("-fx-background-color: #000000;");
    }

    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        gameLoop.start();
    }

    private void update() {
        gameContext.update();
        
        // Mouvement du joueur avec les flèches
        handlePlayerMovement();
        
        // Mouvement des ennemis
        handleEnemyMovement();
        
        // Mise à jour des projectiles
        updateProjectiles();
        
        // Détection des collisions
        checkCollisions();
    }

    private void handlePlayerMovement() {
        // Gauche
        if (activeKeys.contains(KeyCode.LEFT) || activeKeys.contains(KeyCode.Q)) {
            playerX = Math.max(20, playerX - playerSpeed);
        }
        // Droite
        if (activeKeys.contains(KeyCode.RIGHT) || activeKeys.contains(KeyCode.D)) {
            playerX = Math.min(750, playerX + playerSpeed);
        }
        // Haut
        if (activeKeys.contains(KeyCode.UP) || activeKeys.contains(KeyCode.Z)) {
            playerY = Math.max(300, playerY - playerSpeed);
        }
        // Bas
        if (activeKeys.contains(KeyCode.DOWN) || activeKeys.contains(KeyCode.S)) {
            playerY = Math.min(550, playerY + playerSpeed);
        }
    }

    private void handleEnemyMovement() {
        // Mouvement de groupe des ennemis (style Space Invaders)
        if (movingRight) {
            for (int i = 0; i < enemies.length; i++) {
                enemyX[i] += enemySpeed;
                if (enemyX[i] > 700) {
                    movingRight = false;
                    for (int j = 0; j < enemies.length; j++) {
                        enemyY[j] += 20; // Descendre d'un niveau
                    }
                    break;
                }
            }
        } else {
            for (int i = 0; i < enemies.length; i++) {
                enemyX[i] -= enemySpeed;
                if (enemyX[i] < 50) {
                    movingRight = true;
                    for (int j = 0; j < enemies.length; j++) {
                        enemyY[j] += 20; // Descendre d'un niveau
                    }
                    break;
                }
            }
        }
    }

    private void updateProjectiles() {
        // Mettre à jour tous les projectiles actifs
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile p = projectiles.get(i);
            p.update();
            
            // Supprimer les projectiles inactifs
            if (!p.active) {
                projectiles.remove(i);
            }
        }
    }

    private void checkCollisions() {
        // Vérifier les collisions entre projectiles et ennemis
        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile p = projectiles.get(i);
            
            for (int j = 0; j < enemies.length; j++) {
                if (enemies[j] != null) {
                    // Collision simple (rectangle-rectangle)
                    if (p.x >= enemyX[j] - 15 && p.x <= enemyX[j] + 15 &&
                        p.y >= enemyY[j] - 10 && p.y <= enemyY[j] + 10) {
                        
                        GameLogger.log("COLLISION", "Projectile hit " + enemies[j].getName());
                        
                        // Effet visuel de collision
                        createExplosion(enemyX[j], enemyY[j]);
                        
                        // Désactiver le projectile
                        p.active = false;
                        
                        // "Détruire" l'ennemi (le cacher)
                        enemies[j] = null;
                        
                        break;
                    }
                }
            }
        }
    }

    private void createExplosion(double x, double y) {
        // Créer un effet d'explosion temporaire
        // Pour l'instant, on va juste logger l'événement
        GameLogger.log("EFFECT", "Explosion at (" + x + ", " + y + ")");
    }

    private void render() {
        // Effacer l'écran
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 800, 600);
        
        // Dessiner les étoiles
        drawStars();
        
        // Dessiner le joueur
        drawPlayer();
        
        // Dessiner les ennemis
        drawEnemies();
        
        // Dessiner les projectiles
        drawProjectiles();
        
        // Informations des patterns
        drawPatternsInfo();
        
        // Instructions de contrôle
        drawControlsInfo();
    }

    private void drawStars() {
        gc.setFill(Color.WHITE);
        for (int i = 0; i < 50; i++) {
            double x = (i * 37) % 800;
            double y = (i * 29) % 600;
            double size = (i % 2) + 1;
            gc.fillOval(x, y, size, size);
        }
    }

    private void drawPlayer() {
        // Corps du vaisseau
        gc.setFill(Color.BLUE);
        gc.fillRect(playerX - 15, playerY - 10, 30, 20);
        
        // Cockpit
        gc.setFill(Color.CYAN);
        gc.fillRect(playerX - 8, playerY - 8, 16, 8);
        
        // Propulseurs
        gc.setFill(Color.ORANGE);
        gc.fillRect(playerX - 12, playerY + 10, 8, 5);
        gc.fillRect(playerX + 4, playerY + 10, 8, 5);
        
        // Indicateur de tir si des projectiles sont actifs
        if (!projectiles.isEmpty()) {
            gc.setFill(Color.YELLOW);
            gc.fillOval(playerX - 3, playerY - 15, 6, 6);
        }
    }

    private void drawEnemies() {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                // Choisir la couleur selon le type d'ennemi
                String enemyName = enemies[i].getName();
                if (enemyName.contains("Fast")) {
                    gc.setFill(Color.RED); // Ennemi rapide = rouge
                } else if (enemyName.contains("Tank")) {
                    gc.setFill(Color.GREEN); // Ennemi tank = vert
                } else {
                    gc.setFill(Color.YELLOW); // Ennemi basique = jaune
                }
                
                // Dessiner l'ennemi
                gc.fillRect(enemyX[i] - 12, enemyY[i] - 8, 24, 16);
                
                // Détails
                gc.setFill(Color.BLACK);
                gc.fillRect(enemyX[i] - 8, enemyY[i] - 4, 16, 4);
            }
        }
    }

    private void drawProjectiles() {
        for (Projectile p : projectiles) {
            p.draw(gc);
        }
    }

    private void drawPatternsInfo() {
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Arial", 14));
        gc.fillText("État du jeu: " + gameContext.getState().getStateName(), 20, 30);
        
        // Informations sur les patterns actifs
        gc.setFill(Color.CYAN);
        gc.fillText("🎯 STATE: Navigation écrans", 20, 250);
        gc.fillText("🎨 DECORATOR: Power-ups joueur", 20, 270);
        gc.fillText("🏗️ COMPOSITE: Structure ennemis", 20, 290);
        gc.fillText("🏭 FACTORY: Création ennemis", 20, 310);
        
        // Informations de debug
        gc.setFill(Color.LIGHTGREEN);
        gc.fillText("Projectiles actifs: " + projectiles.size(), 20, 330);
    }

    private void drawControlsInfo() {
        gc.setFill(Color.LIGHTGREEN);
        gc.setFont(Font.font("Arial", 12));
        gc.fillText("🎮 CONTRÔLES ACTIFS:", 20, 350);
        gc.fillText("• FLÈCHES ou ZQSD = Déplacer vaisseau", 40, 370);
        gc.fillText("• ESPACE = Tirer (proj. jaune/orange)", 40, 385);
        gc.fillText("• P = Écran Pause", 40, 400);
        gc.fillText("• ESC = Menu principal", 40, 415);
        
        // Afficher la position du joueur (debug)
        gc.setFill(Color.YELLOW);
        gc.fillText(String.format("Position: (%.0f, %.0f)", playerX, playerY), 20, 450);
        
        // Instructions ESPACE
        if (projectiles.isEmpty()) {
            gc.setFill(Color.ORANGE);
            gc.fillText("💡 Appuyez sur ESPACE pour tirer!", 20, 480);
        }
    }

    private void setupInputHandlers() {
        // S'assurer que la vue peut recevoir le focus
        view.setFocusTraversable(true);
        
        // Gestionnaire pour les touches pressées
        view.setOnKeyPressed(e -> {
            GameLogger.log("DEBUG", "Touche pressée: " + e.getCode());
            activeKeys.add(e.getCode());
            
            if (e.getCode() == KeyCode.ESCAPE) {
                GameLogger.log("STATE", "GUI: Game -> Menu");
                gameLoop.stop();
                gameManager.showMenuView();
            } else if (e.getCode() == KeyCode.P) {
                GameLogger.log("STATE", "GUI: Game -> Pause");
                gameLoop.stop();
                gameManager.showPauseView();
            } else if (e.getCode() == KeyCode.SPACE) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastShotTime > SHOT_COOLDOWN) {
                    GameLogger.log("INFO", "GUI: Tir avec ESPACE");
                    shootProjectile();
                    lastShotTime = currentTime;
                }
            }
        });
        
        // Gestionnaire pour les touches relâchées
        view.setOnKeyReleased(e -> {
            activeKeys.remove(e.getCode());
        });
        
        // FORCER le focus immédiatement
        Platform.runLater(() -> {
            view.requestFocus();
            System.out.println("🎮 SYSTÈME DE TIR ACTIVÉ!");
            System.out.println("   • FLÈCHES/ZQSD = Déplacement vaisseau");
            System.out.println("   • ESPACE = Tirer (projectiles visibles)");
            System.out.println("   • P = Pause");
            System.out.println("   • ESC = Menu");
            System.out.println("   • Les tirs détruisent les ennemis!");
        });
    }
    
    private void shootProjectile() {
        // Créer un nouveau projectile depuis le vaisseau
        Projectile newProjectile = new Projectile(playerX, playerY - 15);
        projectiles.add(newProjectile);
        
        GameLogger.log("PROJECTILE", "Nouveau projectile créé: " + projectiles.size() + " actifs");
    }

    public Pane getView() {
        return view;
    }
}