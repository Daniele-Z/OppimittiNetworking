<?php
  
  $conn = mysqli_connect('localhost', 'root', '', 'sqd');
  if (!$conn) {
    die('Connessione al database fallita: ' . mysqli_connect_error());
  }


  $id = $_GET['id'];


  $query = "SELECT * FROM dipendenti WHERE id = $id";
  $result = mysqli_query($conn, $query);
  $row = mysqli_fetch_assoc($result);

  
  mysqli_close($conn);
?>

<!DOCTYPE html>
<html>
<head>
  <style>
    
    form {
      margin-top: 20px;
    }
    label {
      display: block;
      margin-bottom: 5px;
    }
    input[type="submit"] {
      margin-top: 10px;
    }
  </style>
</head>
<body>
  <h2>Modifica Dipendente</h2>
  <form method="POST" action="salva_modifica.php">
    <input type="hidden" name="id" value="<?php echo $row['id']; ?>">
    <label for="nome">Nome:</label>
    <input type="text" id="nome" name="Nome" value="<?php echo $row['Nome']; ?>" required>
    <label for="cognome">Cognome:</label>
    <input type="text" id="cognome" name="Cognome" value="<?php echo $row['Cognome']; ?>" required>
    <label for="sesso">Sesso:</label>
    <input type="text" id="sesso" name="Sesso" value="<?php echo $row['Sesso']; ?>" required>
    <label for="nascita">Nascita:</label>
    <input type="text" id="nascita" name="Nascita" value="<?php echo $row['Nascita']; ?>" required>
    <label for="stipendio">Stipendio:</label>
    <input type="text" id="stipendio" name="Stipendio" value="<?php echo $row['Stipendio']; ?>" required>
    <label for="impiego">Impiego:</label>
    <input type="text" id="impiego" name="Impiego" value="<?php echo $row['Impiego']; ?>" required>
    <input type="submit" value="Salva Modifiche">
  </form>
  
</body>

</html>