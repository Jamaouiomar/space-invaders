# 🚀 Space Invaders - Design Patterns Edition

<div align="center">

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-blue)
![JavaFX](https://img.shields.io/badge/JavaFX-17-purple)
![Design Patterns](https://img.shields.io/badge/Design%20Patterns-4-green)

**Un jeu Space Invaders développé pour démontrer l'application pratique de 4 Design Patterns**

[Problèmes](https://github.com/ton-username/space-invaders/issues) • [Discussions](https://github.com/ton-username/space-invaders/discussions)

</div>

## 🎯 Présentation du Projet

Ce projet a été développé dans le cadre du module **Design Patterns** pour illustrer l'application concrète de 4 patterns fondamentaux dans un jeu vidéo fonctionnel.

### 🏗️ Architecture et Patterns Implémentés

| Pattern | Application dans le Projet | Avantages |
|---------|---------------------------|-----------|
| **🎯 State** | Gestion des écrans (Menu, Jeu, Pause, Game Over) | Navigation fluide, logique séparée |
| **🎨 Decorator** | Système de power-ups (Speed Boost, Shield) | Ajout dynamique de capacités |
| **🏗️ Composite** | Structure hiérarchique des niveaux et ennemis | Gestion unifiée des éléments |
| **🏭 Factory** | Création de différents types d'ennemis | Encapsulation, extensibilité |

## 🎮 Fonctionnalités

### ✨ Interface Graphique Complète
- ✅ **Menu principal** avec navigation intuitive
- ✅ **Écran de jeu** avec rendu temps réel
- ✅ **Système HUD** affichant score et état
- ✅ **Écrans de transition** Pause et Game Over

### 🕹️ Gameplay
- ✅ **Contrôles fluides** (Flèches/ZQSD + ESPACE)
- ✅ **Système de tir** avec projectiles
- ✅ **Ennemis variés** (Basic, Fast, Tank)
- ✅ **Mouvement intelligent** des ennemis
- ✅ **Détection de collisions**

### 📊 Système Technique
- ✅ **Logging complet** avec Log4j2
- ✅ **Architecture modulaire** et extensible
- ✅ **Gestion d'erreurs** robuste
- ✅ **Code documenté** et structuré

## 🚀 Installation et Exécution

### Prérequis
- **Java JDK 17** ou supérieur
- **Maven 3.6** ou supérieur
- **Git** (pour cloner le projet)

### Méthode 1 : Exécution avec Maven
```bash
# Cloner le projet
git clone https://github.com/Jamaouiomar
/space-invaders.git
cd space-invaders

# Compiler et exécuter (mode graphique)
mvn clean compile exec:java

# Mode console (tests des patterns)
mvn exec:java -Dexec.args="console"
