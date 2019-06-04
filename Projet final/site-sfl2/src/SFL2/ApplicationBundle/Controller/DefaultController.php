<?php

namespace SFL2\ApplicationBundle\Controller;
use SFL2\ApplicationBundle\Entity\Utilisateur;
use SFL2\ApplicationBundle\Entity\Emprunt;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use \SFL2\ApplicationBundle\Repository\UtilisateurRepository;
class DefaultController extends Controller
{
    public function indexAction()
    {
        //return $this->render('SFL2ApplicationBundle:Default:index.html.twig');
        return $this->redirectToRoute('sfl2_login');
        
        
    }

    public function accueilAction()
    {
        $em = $this->getDoctrine()->getManager();

        $users = $em->getRepository('SFL2ApplicationBundle:Utilisateur')->findAll();
        $emprunt = $em->getRepository('SFL2ApplicationBundle:Emprunt')->findAll();
        $nombre =$em->getRepository(Utilisateur::class)->findAll();

        return $this->render('SFL2ApplicationBundle:Default:index.html.twig', array(
                    'utilisateurs' => $users,
                    'nombre' =>$nombre,
                    'emprunt' =>$emprunt,
        ));
        
  
    }
}
