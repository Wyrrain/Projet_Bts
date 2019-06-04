<?php

namespace SFL2\ApplicationBundle\Controller;

use SFL2\ApplicationBundle\Entity\Utilisateur;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation;
use Symfony\Component\HttpFoundation\Request;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Security;

class UtilisateurController extends Controller {

    public function indexAction() {

        $em = $this->getDoctrine()->getManager();

        $users = $em->getRepository('SFL2ApplicationBundle:Utilisateur')->findAll();

        return $this->render('SFL2ApplicationBundle:Utilisateur:index.html.twig', array(
                    'utilisateurs' => $users,
        ));
    }
    
    /**
    * @Security("has_role('ROLE_ADMIN')")
    */
    public function addAction(Request $request) {

        $user = new Utilisateur();
        
        $form = $this->createForm('SFL2\ApplicationBundle\Form\UtilisateurType', $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();

            $em->persist($user);
            $em->flush();

            $this->addFlash("success", "<em>'" . $user->getPrenomNom() . "'</em> a été ajouté avec succès.");

            return $this->redirectToRoute('sfl2_utilisateurs_index');
        }

        return $this->render('SFL2ApplicationBundle:Utilisateur:add.html.twig', array('form' => $form->createView()));
    }
    
    /**
    * @Security("has_role('ROLE_ADMIN')")
    */
    public function editAction(HttpFoundation\Request $request, Utilisateur $user) {

        $form = $this->createForm('SFL2\ApplicationBundle\Form\UtilisateurType', $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();

            $em->persist($user);
            $em->flush();

            $this->addFlash("success", "<em>'" . $user->getPrenomNom() . "'</em> a été modifié avec succès.");

            return $this->redirectToRoute('sfl2_utilisateurs_index');
        }

        return $this->render('SFL2ApplicationBundle:Utilisateur:add.html.twig', array('form' => $form->createView()));
    }
    
    /**
    * @Security("has_role('ROLE_ADMIN')")
    */
    public function deleteAction(Utilisateur $user) {
        
 
        $em = $this->getDoctrine()->getManager();

        $em->remove($user);
        $em->flush();

        $this->addFlash("success", "Utilisateur supprimé avec succès.");

        return $this->redirectToRoute('sfl2_utilisateurs_index');
        
        
    }

    

}
