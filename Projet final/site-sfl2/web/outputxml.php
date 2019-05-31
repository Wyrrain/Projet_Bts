<?php
require("connexionbdd.php");

function parseToXML($htmlStr)
{
$xmlStr=str_replace('<','&lt;',$htmlStr);
$xmlStr=str_replace('>','&gt;',$xmlStr);
$xmlStr=str_replace('"','&quot;',$xmlStr);
$xmlStr=str_replace("'",'&#39;',$xmlStr);
$xmlStr=str_replace("&",'&amp;',$xmlStr);
return $xmlStr;
}

// Opens a connection to a MySQL server
$connection=mysqli_connect ('localhost', $username, $password);
if (!$connection) {
  die('Not connected : ' . mysqli_error());
}

// Set the active MySQL database
$db_selected = mysqli_select_db($connection,"bdd_rfid");
if (!$db_selected) {
  die ('Can\'t use db : ' . mysqli_error());
}

// Select all the rows in the markers table
$query = "SELECT utilisateur.prenom as prenom ,utilisateur.nom as nom, position.id, position.longitude, position.lattitude FROM `position` Left join emprunt ON emprunt_id = emprunt.id Left join utilisateur ON utilisateur.id = utilisateur_id WHERE 1";
$result = mysqli_query($connection,$query);
if (!$result) {
  die('Invalid query: ' . mysqli_error());
}

header("Content-type: text/xml");

// Start XML file, echo parent node
echo "<?xml version='1.0' ?>";
echo '<markers>';
$ind=0;
// Iterate through the rows, printing XML nodes for each
while ($row = mysqli_fetch_array($result)){
  // Add to XML document node
  echo '<marker ';
  echo 'id="' . $row['id'] . '" ';
  echo 'prenom="' . parseToXML($row['prenom']).'" ';
  echo 'nom="' . parseToXML($row['nom']).'" ';
  echo 'lat="' . $row['lattitude'] . '" ';
  echo 'lng="' . $row['longitude'] . '" ';
  echo '/>';
  $ind = $ind + 1;
}

// End XML file
echo '</markers>';

?>