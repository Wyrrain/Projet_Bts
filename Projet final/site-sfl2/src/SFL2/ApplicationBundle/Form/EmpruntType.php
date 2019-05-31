<?php

namespace SFL2\ApplicationBundle\Form;

use SFL2\ApplicationBundle\Repository\EmpruntRepository;
use SFL2\ApplicationBundle\Entity\Emprunt;
use SFL2\ApplicationBundle\Entity\Article;
use SFL2\ApplicationBundle\Entity\Utilisateur;
use Symfony\Component\Form\AbstractType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use \Doctrine\ORM\EntityRepository;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class EmpruntType extends AbstractType {

    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options) {
        $builder ->add('debut', DateType::class,array(
                        
                        'input' => 'datetime',
                        'format' => 'ddMMyyyy',
                        
                        ))
                ->add('fin', DateType::class,array(
                        
                        'input' => 'datetime',
                        'format' => 'ddMMyyyy',
                        
                        ))
                
                ->add('article', EntityType::class, array(
                    'class' => 'SFL2ApplicationBundle:Article',
                    'choice_label' => function ($e) {
                        return $e->getNomMarque();
                    }, 
                    'query_builder'=> function(EntityRepository $er){
                        return $er->createQueryBuilder('e')
                                ->where('e.disponible != 0')
                                ->orderBy('e.nom', 'ASC');
                                
                    },
                     
                    'required' => true,
                    'multiple' => false,
                ))
              ->add('utilisateur', EntityType::class, array(
                    'class' => 'SFL2ApplicationBundle:Utilisateur',
                    'choice_label' => function ($e) {
                        return $e->getNomPrenom();
                    }, 
                    'required' => true,
                    'multiple' => false,
                ))
                
                ->add('ajouter', SubmitType::class, array('label' => 'Enregistrer', 'attr' => array('class' => 'btn btn-block btn-lg btn-success')));
        
    }

    /**
     * {@inheritdoc}
     */
    public function configureOptions(OptionsResolver $resolver) {
        $resolver->setDefaults(array(
            'data_class' => Emprunt::class
        ));
    }

    /**
     * {@inheritdoc}
     */
    public function getBlockPrefix() {
        return 'sfl2_emprunt';
    }

}
