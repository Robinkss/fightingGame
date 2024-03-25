from PIL import Image, ImageOps

for i in range(8):
    # Ouvrir l'image d'origine
    img = Image.open(f'../run/run_{i}.png')

    # Créer une copie en miroir de l'image
    mirrored_img = ImageOps.mirror(img)

    # Enregistrer la nouvelle image dans un fichier séparé
    mirrored_img.save(f'../run/run_{i}_mirrored.png')