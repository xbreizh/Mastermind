Prérequis:
JRE(Java Runtime Environment) 
https://docs.oracle.com/goldengate/1212/gg-winux/GDRAD/java.htm#BGBFJHAB

Comment lancer l'application:

Pour lancer l'application, vous devez télécharger le dossier.

Vous devez bien faire attention à avoir les dossiers Files et log dans le même répertoire que MasterMind.jar. ils contiennent les fichiers de configuration et de récupérationd de logs nécéssaires à l'application.

Puis, depuis une invite de commande (Windows + R sur windows), naviguez jusqu'à ce répertoire et lancez la commande java -r MasterMind.jar.

L'application se lancera alors

Mode Developpeur:

Afin de passer en mode développeur, vous devez stopper l'application puis éditer le fichier log4j.xml et à la ligne 4, changer debug="false"> for debug="true">
Vous pouvez ensuite relancer l'application
