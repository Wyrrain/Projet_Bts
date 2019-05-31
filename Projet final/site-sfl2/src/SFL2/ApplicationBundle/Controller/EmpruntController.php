<?php

namespace SFL2\ApplicationBundle\Controller;

use SFL2\ApplicationBundle\Entity\Emprunt;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Security;

class EmpruntController extends Controller {

    public function IndexAction() {
        $em = $this->getDoctrine()->getManager();
        $emprunts = $em->getRepository('SFL2ApplicationBundle:Emprunt')->findAll();

        return $this->render('SFL2ApplicationBundle:Emprunt:index.html.twig', array(
                    'emprunts' => $emprunts,
        ));
    }
    
    /**
    * @Security("has_role('ROLE_ADMIN')")
    */
    public function addAction(Request $request) {
        $emprunt = new Emprunt();

        $emprunt->setDebut(new \DateTime());

        $form = $this->createForm('SFL2\ApplicationBundle\Form\EmpruntType', $emprunt);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();


            $em->persist($emprunt);
            $em->flush();

            return $this->redirectToRoute('sfl2_emprunts_index');
        }

        return $this->render('SFL2ApplicationBundle:Emprunt:add.html.twig', array('form' => $form->createView()));
    }
    
    /**
    * @Security("has_role('ROLE_ADMIN')")
    */
    public function editAction(Request $request, Emprunt $emprunt) {
        $form = $this->createForm('SFL2\ApplicationBundle\Form\EmpruntType', $emprunt);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            $em->persist($emprunt);
            $em->flush();

            $this->addFlash("success", "<em>'" . $emprunt->getArticle() . "'</em> a été modifié avec succès.");

            return $this->redirectToRoute('sfl2_emprunts_index');
        }
        return $this->render('SFL2ApplicationBundle:Emprunt:add.html.twig', array(
                    'form' => $form->createView(),
        ));
    }
    
    /**
    * @Security("has_role('ROLE_ADMIN')")
    */
    public function deleteAction(Emprunt $emprunt) {
        $em = $this->getDoctrine()->getManager();

        $em->remove($emprunt);
        $em->flush();

        $this->addFlash("success", "Emprunt supprimé avec succès.");

        return $this->redirectToRoute('sfl2_emprunts_index');
    }

}
