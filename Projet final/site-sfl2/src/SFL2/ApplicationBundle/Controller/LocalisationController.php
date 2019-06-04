<?php


namespace SFL2\ApplicationBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class LocalisationController extends Controller
{
  public function localisationAction()
  {
    return $this->render('SFL2ApplicationBundle:Localisation:localisation.html.twig');

  
  }
}
